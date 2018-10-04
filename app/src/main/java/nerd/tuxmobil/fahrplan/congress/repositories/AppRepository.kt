package nerd.tuxmobil.fahrplan.congress.repositories

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import info.metadude.android.eventfahrplan.database.extensions.toContentValues
import info.metadude.android.eventfahrplan.database.repositories.AlarmsDatabaseRepository
import info.metadude.android.eventfahrplan.database.repositories.HighlightsDatabaseRepository
import info.metadude.android.eventfahrplan.database.repositories.LecturesDatabaseRepository
import info.metadude.android.eventfahrplan.database.repositories.MetaDatabaseRepository
import info.metadude.android.eventfahrplan.database.sqliteopenhelper.AlarmsDBOpenHelper
import info.metadude.android.eventfahrplan.database.sqliteopenhelper.HighlightDBOpenHelper
import info.metadude.android.eventfahrplan.database.sqliteopenhelper.LecturesDBOpenHelper
import info.metadude.android.eventfahrplan.database.sqliteopenhelper.MetaDBOpenHelper
import info.metadude.android.eventfahrplan.network.repositories.ScheduleNetworkRepository
import info.metadude.android.eventfahrplan.sessionize.SessionizeNetworkRepository
import info.metadude.android.eventfahrplan.sessionize.SessionizeResult
import info.metadude.kotlin.library.sessionize.ApiModule
import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import nerd.tuxmobil.fahrplan.congress.BuildConfig
import nerd.tuxmobil.fahrplan.congress.dataconverters.*
import nerd.tuxmobil.fahrplan.congress.models.Alarm
import nerd.tuxmobil.fahrplan.congress.models.Lecture
import nerd.tuxmobil.fahrplan.congress.models.Meta
import nerd.tuxmobil.fahrplan.congress.net.CustomHttpClient
import nerd.tuxmobil.fahrplan.congress.net.FetchScheduleResult
import nerd.tuxmobil.fahrplan.congress.net.HttpStatus
import nerd.tuxmobil.fahrplan.congress.preferences.SharedPreferencesRepository
import nerd.tuxmobil.fahrplan.congress.serialization.ScheduleChanges
import nerd.tuxmobil.fahrplan.congress.utils.FahrplanMisc
import okhttp3.OkHttpClient
import org.ligi.tracedroid.logging.Log
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException

class AppRepository private constructor(val context: Context) {

    companion object : SingletonHolder<AppRepository, Context>(::AppRepository)

    private val alarmsDBOpenHelper = AlarmsDBOpenHelper(context)
    private val alarmsDatabaseRepository = AlarmsDatabaseRepository(alarmsDBOpenHelper)

    private val highlightDBOpenHelper = HighlightDBOpenHelper(context)
    private val highlightsDatabaseRepository = HighlightsDatabaseRepository(highlightDBOpenHelper)

    private val lecturesDBOpenHelper = LecturesDBOpenHelper(context)
    private val lecturesDatabaseRepository = LecturesDatabaseRepository(lecturesDBOpenHelper)

    private val metaDBOpenHelper = MetaDBOpenHelper(context)
    private val metaDatabaseRepository = MetaDatabaseRepository(metaDBOpenHelper)

    private var sessionizeNetworkRepository: SessionizeNetworkRepository? = null

    private val scheduleNetworkRepository = ScheduleNetworkRepository()

    private val sharedPreferencesRepository = SharedPreferencesRepository(context)

    fun loadSchedule(onFetchingDone: (fetchScheduleResult: FetchScheduleResult) -> Unit,
                     onParsingDone: (result: Boolean, version: String) -> Unit) {

        val hostName = BuildConfig.SESSIONIZE_HOST
        if (sessionizeNetworkRepository == null) {
            val okHttpClient: OkHttpClient?
            try {
                okHttpClient = CustomHttpClient.createHttpClient(hostName)
            } catch (e: KeyManagementException) {
                onFetchingDone(FetchScheduleResult(httpStatus = e.toHttpStatus(), hostName = hostName))
                return
            } catch (e: NoSuchAlgorithmException) {
                onFetchingDone(FetchScheduleResult(httpStatus = e.toHttpStatus(), hostName = hostName))
                return
            }

            val sessionizeService = ApiModule.provideSessionizeService(hostName, okHttpClient)
            sessionizeNetworkRepository = SessionizeNetworkRepository(
                    sessionizeService, BuildConfig.SESSIONIZE_API_KEY)
        }

        launch(CommonPool) {
            sessionizeNetworkRepository?.loadConferenceDays { result ->
                when (result) {
                    is SessionizeResult.Values -> {
                        storeConferenceDays(result.conferenceDays)
                        onFetchingDone(
                                FetchScheduleResult(
                                        httpStatus = HttpStatus.HTTP_OK,
                                        hostName = hostName
                                ))
                        onParsingDone(true, "1.0.0")
                    }
                    is SessionizeResult.Error -> {
                        Log.e(javaClass.name, result.toString())
                        onFetchingDone(
                                FetchScheduleResult(
                                        httpStatus = result.httpStatus,
                                        hostName = hostName
                                ))
                    }
                    is SessionizeResult.Exception -> {
                        Log.e(javaClass.name, result.toString())
                        result.throwable.printStackTrace()
                        onFetchingDone(
                                FetchScheduleResult(
                                        httpStatus = result.throwable.toHttpStatus(),
                                        hostName = hostName,
                                        exceptionMessage = result.throwable.message ?: ""
                                ))
                    }
                }
            }
        }
    }

    private fun storeConferenceDays(conferenceDays: List<ConferenceDay>) {
        val metaAppModel = conferenceDays.toMetaAppModel()
        updateMeta(metaAppModel.copy(version = "1.0.0"))
        val eventAppModels = conferenceDays.toEventAppModels()
        val oldLectures = FahrplanMisc.loadLecturesForAllDays(context)
        val hasChanged = ScheduleChanges.hasScheduleChanged(eventAppModels, oldLectures)
        if (hasChanged) {
            resetChangesSeenFlag()
        }
        updateLectures(eventAppModels)
    }


    fun loadSchedule(url: String,
                     eTag: String,
                     onFetchingDone: (fetchScheduleResult: FetchScheduleResult) -> Unit,
                     onParsingDone: (result: Boolean, version: String) -> Unit
    ) {

        val scheduleUrl = readScheduleUrl()
        val hostName = Uri.parse(scheduleUrl).host
        val okHttpClient: OkHttpClient?
        try {
            okHttpClient = CustomHttpClient.createHttpClient(hostName)
        } catch (e: KeyManagementException) {
            onFetchingDone(FetchScheduleResult(httpStatus = HttpStatus.HTTP_SSL_SETUP_FAILURE, hostName = hostName))
            return
        } catch (e: NoSuchAlgorithmException) {
            onFetchingDone(FetchScheduleResult(httpStatus = HttpStatus.HTTP_SSL_SETUP_FAILURE, hostName = hostName))
            return
        }

        // Fetching
        scheduleNetworkRepository.fetchSchedule(okHttpClient, url, eTag) { fetchScheduleResult ->
            onFetchingDone.invoke(fetchScheduleResult.toAppFetchScheduleResult())

            if (fetchScheduleResult.isSuccessful()) {
                // Parsing
                scheduleNetworkRepository.parseSchedule(fetchScheduleResult.scheduleXml, fetchScheduleResult.eTag,
                        onUpdateLectures = { lectures ->
                            val oldLectures = FahrplanMisc.loadLecturesForAllDays(context)
                            val newLectures = lectures.toLecturesAppModel2()
                            val hasChanged = ScheduleChanges.hasScheduleChanged(newLectures, oldLectures)
                            if (hasChanged) {
                                resetChangesSeenFlag()
                            }
                            updateLectures(newLectures)
                        },
                        onUpdateMeta = { meta ->
                            updateMeta(meta.toMetaAppModel())
                        },
                        onParsingDone = { result: Boolean, version: String ->
                            onParsingDone(result, version)
                        })
            }
        }
    }

    @JvmOverloads
    fun readAlarms(eventId: String = "") = if (eventId.isEmpty()) {
        alarmsDatabaseRepository.query().toAlarmsAppModel()
    } else {
        alarmsDatabaseRepository.query(eventId).toAlarmsAppModel()
    }

    @JvmOverloads
    fun deleteAlarmForAlarmId(alarmId: Int, closeSQLiteOpenHelper: Boolean = true) =
            alarmsDatabaseRepository.deleteForAlarmId(alarmId, closeSQLiteOpenHelper)

    fun deleteAlarmForEventId(eventId: String) =
            alarmsDatabaseRepository.deleteForEventId(eventId)

    fun updateAlarm(alarm: Alarm) {
        val alarmDatabaseModel = alarm.toAlarmDatabaseModel()
        val values = alarmDatabaseModel.toContentValues()
        alarmsDatabaseRepository.insert(values, alarm.eventId)
    }

    fun readHighlights() =
            highlightsDatabaseRepository.query().toHighlightsAppModel()

    fun updateHighlight(lecture: Lecture) {
        val highlightDatabaseModel = lecture.toHighlightDatabaseModel()
        val values = highlightDatabaseModel.toContentValues()
        highlightsDatabaseRepository.insert(values, lecture.lecture_id)
    }

    fun readLecturesForDayIndexOrderedByDateUtc(dayIndex: Int) =
            lecturesDatabaseRepository.queryLecturesForDayIndexOrderedByDateUtc(dayIndex).toLecturesAppModel()

    fun readLecturesOrderedByDateUtc() =
            lecturesDatabaseRepository.queryLecturesOrderedByDateUtc().toLecturesAppModel()

    fun readDateInfos() =
            readLecturesOrderedByDateUtc().toDateInfos()

    private fun updateLectures(lectures: List<Lecture>) {
        val lecturesDatabaseModel = lectures.toLecturesDatabaseModel()
        val list = lecturesDatabaseModel.map { it.toContentValues() }
        lecturesDatabaseRepository.insert(list)
    }

    fun readMeta() =
            metaDatabaseRepository.query().toMetaAppModel()

    private fun updateMeta(meta: Meta) {
        val metaDatabaseModel = meta.toMetaDatabaseModel()
        val values = metaDatabaseModel.toContentValues()
        metaDatabaseRepository.insert(values)
    }

    fun readScheduleUrl(): String {
        val alternateScheduleUrl = sharedPreferencesRepository.getScheduleUrl()
        return if (TextUtils.isEmpty(alternateScheduleUrl)) {
            BuildConfig.SCHEDULE_URL
        } else {
            alternateScheduleUrl
        }
    }

    private fun resetChangesSeenFlag() =
            sharedPreferencesRepository.setChangesSeen(false)

}
