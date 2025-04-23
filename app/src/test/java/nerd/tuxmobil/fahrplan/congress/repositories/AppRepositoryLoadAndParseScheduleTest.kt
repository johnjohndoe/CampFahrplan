package nerd.tuxmobil.fahrplan.congress.repositories

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import info.metadude.android.eventfahrplan.commons.testing.MainDispatcherTestExtension
import info.metadude.android.eventfahrplan.commons.testing.verifyInvokedNever
import info.metadude.android.eventfahrplan.commons.testing.verifyInvokedOnce
import info.metadude.android.eventfahrplan.database.repositories.AlarmsDatabaseRepository
import info.metadude.android.eventfahrplan.database.repositories.HighlightsDatabaseRepository
import info.metadude.android.eventfahrplan.database.repositories.MetaDatabaseRepository
import info.metadude.android.eventfahrplan.database.repositories.SessionsDatabaseRepository
import info.metadude.android.eventfahrplan.network.models.HttpHeader
import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import info.metadude.kotlin.library.sessionize.repositories.SessionizeRepository
import info.metadude.kotlin.library.sessionize.repositories.models.GetConferenceDaysState.Error
import info.metadude.kotlin.library.sessionize.repositories.models.GetConferenceDaysState.Success
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import nerd.tuxmobil.fahrplan.congress.TestExecutionContext
import nerd.tuxmobil.fahrplan.congress.dataconverters.toAppFetchScheduleResult
import nerd.tuxmobil.fahrplan.congress.exceptions.ExceptionHandling
import nerd.tuxmobil.fahrplan.congress.models.ScheduleData
import nerd.tuxmobil.fahrplan.congress.net.FetchScheduleResult
import nerd.tuxmobil.fahrplan.congress.net.HttpStatus
import nerd.tuxmobil.fahrplan.congress.net.ParseResult
import nerd.tuxmobil.fahrplan.congress.net.ParseScheduleResult
import nerd.tuxmobil.fahrplan.congress.preferences.SharedPreferencesRepository
import nerd.tuxmobil.fahrplan.congress.repositories.LoadScheduleState.FetchFailure
import nerd.tuxmobil.fahrplan.congress.repositories.LoadScheduleState.Fetching
import nerd.tuxmobil.fahrplan.congress.repositories.LoadScheduleState.InitialFetching
import nerd.tuxmobil.fahrplan.congress.repositories.LoadScheduleState.ParseSuccess
import okhttp3.OkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month.JANUARY
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import kotlin.coroutines.CoroutineContext
import info.metadude.android.eventfahrplan.database.models.Highlight as DatabaseHighlight
import info.metadude.android.eventfahrplan.database.models.Meta as DatabaseMeta
import info.metadude.android.eventfahrplan.database.models.Session as DatabaseSession
import info.metadude.android.eventfahrplan.network.fetching.FetchScheduleResult as NetworkFetchScheduleResult
import info.metadude.android.eventfahrplan.network.fetching.HttpStatus as NetworkHttpStatus
import nerd.tuxmobil.fahrplan.congress.models.Session as AppSession

private typealias OnFetchingDone = (fetchScheduleResult: FetchScheduleResult) -> Unit
private typealias OnParsingDone = (parseScheduleResult: ParseResult) -> Unit

/**
 * Covers [AppRepository.loadSchedule] and the private `parseSchedule` function.
 *
 * The following public properties are checked:
 * - [AppRepository.loadScheduleState]
 * - [AppRepository.starredSessions]
 * - [AppRepository.changedSessions]
 * - [AppRepository.selectedSession]
 * - [AppRepository.uncanceledSessionsForDayIndex]
 */
@ExtendWith(MainDispatcherTestExtension::class)
class AppRepositoryLoadAndParseScheduleTest {

    private companion object {
        const val HOST_NAME = "https://example.com"
        const val EMPTY_ENGELSYSTEM_URL = ""
    }

    private val alarmsDatabaseRepository = mock<AlarmsDatabaseRepository>()
    private val highlightsDatabaseRepository = mock<HighlightsDatabaseRepository>()
    private val sessionsDatabaseRepository = mock<SessionsDatabaseRepository>()
    private val metaDatabaseRepository = mock<MetaDatabaseRepository>()
    private val sessionizeRepository = mock<SessionizeRepository>()
    private val sharedPreferencesRepository = mock<SharedPreferencesRepository>()
    private val sessionsTransformer = mock<SessionsTransformer>()

    private val testableAppRepository: AppRepository
        get() = with(AppRepository) {
            val exceptionHandler = object : ExceptionHandling {
                override fun onExceptionHandling(context: CoroutineContext, throwable: Throwable) {}
            }
            initialize(
                context = mock(),
                logging = mock(),
                executionContext = TestExecutionContext,
                databaseScope = mock(),
                networkScope = NetworkScope.of(TestExecutionContext, exceptionHandler),
                okHttpClient = OkHttpClient(),
                alarmsDatabaseRepository = alarmsDatabaseRepository,
                highlightsDatabaseRepository = highlightsDatabaseRepository,
                sessionsDatabaseRepository = sessionsDatabaseRepository,
                metaDatabaseRepository = metaDatabaseRepository,
                scheduleNetworkRepository = mock(),
                sessionizeNetworkRepository = sessionizeRepository,
                engelsystemNetworkRepository = mock(),
                sharedPreferencesRepository = sharedPreferencesRepository,
                sessionsTransformer = sessionsTransformer
            )
            return this
        }

    @Test
    fun `loadScheduleState emits InitialFetching when invoking loadSchedule for the first time`() =
        runTest {
            whenever(metaDatabaseRepository.query()) doReturn DatabaseMeta(numDays = 0)
            testableAppRepository.loadSchedule(isUserRequest = false)
            testableAppRepository.loadScheduleState.test {
                val actualState = awaitItem()
                assertThat(actualState).isEqualTo(InitialFetching)
            }
        }

    @Test
    fun `loadScheduleState emits Fetching when invoking loadSchedule once a schedule has been stored`() =
        runTest {
            whenever(metaDatabaseRepository.query()) doReturn DatabaseMeta(numDays = 1)
            testableAppRepository.loadSchedule(isUserRequest = false)
            testableAppRepository.loadScheduleState.test {
                val actualState = awaitItem()
                assertThat(actualState).isEqualTo(Fetching)
            }
        }

    @Test
    fun `loadScheduleState emits ParseSuccess HTTP 200 when schedule has been loaded and parsed`() =
        runTest {
            whenever(metaDatabaseRepository.query()) doReturn DatabaseMeta(numDays = 1)
            val success = createFetchScheduleResult(NetworkHttpStatus.HTTP_OK)
            val onFetchingDone: OnFetchingDone = { result ->
                assertThat(result).isEqualTo(success.toAppFetchScheduleResult())
            }
            val onParsingDone: OnParsingDone = { result ->
                assertThat(result).isEqualTo(ParseScheduleResult(true, "1"))
            }
            whenever(sessionizeRepository.getConferenceDaysState(
                requestETag = any(),
                lastModifiedAt = any(),
            )) doReturn flowOf(Success(
                conferenceDays = emptyList(),
                responseETag = "",
                responseLastModifiedAt = "",
            ))
            testableAppRepository.loadSchedule(isUserRequest = false, onFetchingDone, onParsingDone)
            testableAppRepository.loadScheduleState.test {
                assertThat(awaitItem()).isEqualTo(ParseSuccess)
            }
            verifyInvokedOnce(sharedPreferencesRepository).setScheduleLastFetchedAt(any())
            verifyInvokedOnce(metaDatabaseRepository).insert(any())
        }

    @Test
    fun `loadScheduleState emits FetchFailure HTTP 304 when schedule has not been modified`() =
        runTest {
            whenever(metaDatabaseRepository.query()) doReturn DatabaseMeta(numDays = 1)
            whenever(sharedPreferencesRepository.getEngelsystemShiftsUrl()) doReturn EMPTY_ENGELSYSTEM_URL // early exit to bypass here
            val notModified = createFetchScheduleResult(NetworkHttpStatus.HTTP_NOT_MODIFIED)
            val onFetchingDone: OnFetchingDone = { result ->
                assertThat(result).isEqualTo(notModified.toAppFetchScheduleResult())
            }
            whenever(sessionizeRepository.getConferenceDaysState(
                requestETag = any(),
                lastModifiedAt = any(),
            )) doReturn flowOf(Error(
                httpStatusCode = 304,
                errorMessage = "",
            ))
            testableAppRepository.loadSchedule(isUserRequest = false, onFetchingDone)
            testableAppRepository.loadScheduleState.test {
                val expected =
                    createFetchFailure(HttpStatus.HTTP_NOT_MODIFIED, isUserRequest = false)
                assertThat(awaitItem()).isEqualTo(expected)
            }
            verifyInvokedOnce(sharedPreferencesRepository).setScheduleLastFetchedAt(any())
            verifyInvokedNever(metaDatabaseRepository).insert(any())
        }

    @Test
    fun `loadScheduleState emits FetchFailure HTTP 404 when schedule cannot be loaded`() = runTest {
        whenever(metaDatabaseRepository.query()) doReturn DatabaseMeta(numDays = 1)
        val notFound = createFetchScheduleResult(NetworkHttpStatus.HTTP_NOT_FOUND)
        val onFetchingDone: OnFetchingDone = { result ->
            assertThat(result).isEqualTo(notFound.toAppFetchScheduleResult())
        }
        whenever(sessionizeRepository.getConferenceDaysState(
            requestETag = any(),
            lastModifiedAt = any(),
        )
        ) doReturn flowOf(Error(
            httpStatusCode = 404,
            errorMessage = "",
        ))
        testableAppRepository.loadSchedule(isUserRequest = true, onFetchingDone)
        testableAppRepository.loadScheduleState.test {
            val expectedResult = createFetchFailure(HttpStatus.HTTP_NOT_FOUND, isUserRequest = true)
            assertThat(awaitItem()).isEqualTo(expectedResult)
        }
        verifyInvokedNever(sharedPreferencesRepository).setScheduleLastFetchedAt(any())
        verifyInvokedNever(metaDatabaseRepository).insert(any())
    }

    /**
     * Quickly passing through the loading part and only checking parsing part here.
     */
    @Test
    fun `loadScheduleState emits ParseSuccess when parsing finished successfully`() =
        runTest {
            whenever(metaDatabaseRepository.query()) doReturn DatabaseMeta(numDays = 1)
            whenever(sessionsDatabaseRepository.querySessionsWithoutRoom(any())) doReturn listOf(
                DatabaseSession("67")
            )
            val onParsingDone: OnParsingDone = { result ->
                assertThat(result).isEqualTo(ParseScheduleResult(isSuccess = true, "1.0.0"))
            }
            whenever(sessionizeRepository.getConferenceDaysState(
                requestETag = any(),
                lastModifiedAt = any(),
            )) doReturn flowOf(Success(
                conferenceDays = createConferenceDays(),
                responseETag = "1.0.1",
                responseLastModifiedAt = "",
            ))
            testableAppRepository.loadSchedule(isUserRequest = false, onParsingDone = onParsingDone)

            // onUpdateSessions
            whenever(sessionsDatabaseRepository.querySessionsOrderedByDateUtc()) doReturn listOf(
                DatabaseSession(sessionId = "55", isHighlight = true, changedLanguage = true)
            )
            whenever(highlightsDatabaseRepository.query()) doReturn emptyList()
            whenever(alarmsDatabaseRepository.query()) doReturn emptyList()

            verifyInvokedOnce(sessionsDatabaseRepository).updateSessions(any(), any())

            assertStarredSessionsProperty()
            assertChangedSessionsProperty()
            assertSelectedSessionProperty()
            assertUncanceledSessionsForDayIndexProperty()

            // onUpdateMeta
            verify(metaDatabaseRepository, times(1)).insert(any())

            // onParsingDone
            whenever(sharedPreferencesRepository.getEngelsystemShiftsUrl()) doReturn EMPTY_ENGELSYSTEM_URL // early exit to bypass here
            testableAppRepository.loadScheduleState.test {
                assertThat(awaitItem()).isEqualTo(ParseSuccess)
            }
        }

    private fun createConferenceDays(): List<ConferenceDay> {
        val zoneId = ZoneId.of("Z")
        return listOf(
            ConferenceDay(
                date = ZonedDateTime.of(LocalDateTime.of(2023, JANUARY, 1, 10, 0, 0), zoneId),
            ),
            ConferenceDay(
                date = ZonedDateTime.of(LocalDateTime.of(2023, JANUARY, 2, 10, 0, 0), zoneId),
            )
        )
    }

    private suspend fun assertStarredSessionsProperty() {
        testableAppRepository.starredSessions.test {
            val session = AppSession(sessionId = "55", isHighlight = true)
            assertThat(awaitItem()).isEqualTo(listOf(session))
        }
    }

    private suspend fun assertChangedSessionsProperty() {
        testableAppRepository.changedSessions.test {
            val session = AppSession(
                sessionId = "55",
                isHighlight = true,
                changedLanguage = true,
            )
            assertThat(awaitItem()).isEqualTo(listOf(session))
        }
    }

    private suspend fun assertSelectedSessionProperty() {
        whenever(sessionsDatabaseRepository.querySessionBySessionId(any())) doReturn DatabaseSession(
            "23"
        )
        whenever(highlightsDatabaseRepository.queryBySessionId(any())) doReturn DatabaseHighlight(
            sessionId = "23",
            isHighlight = false
        )
        whenever(alarmsDatabaseRepository.query(anyString())) doReturn emptyList()
        whenever(sharedPreferencesRepository.getSelectedSessionId()) doReturn "23"
        testableAppRepository.selectedSession.test {
            assertThat(awaitItem()).isEqualTo(AppSession("23"))
        }
    }

    private suspend fun assertUncanceledSessionsForDayIndexProperty() {
        whenever(sessionsTransformer.transformSessions(any(), any())) doReturn ScheduleData(
            dayIndex = 0,
            roomDataList = emptyList()
        )
        testableAppRepository.uncanceledSessionsForDayIndex.test {
            assertThat(awaitItem()).isEqualTo(
                ScheduleData(
                    dayIndex = 0,
                    roomDataList = emptyList()
                )
            )
        }
    }

    private fun createFetchScheduleResult(httpStatus: NetworkHttpStatus) =
        NetworkFetchScheduleResult(
            httpStatus = httpStatus,
            scheduleXml = "some fahrplan xml",
            httpHeader = HttpHeader(eTag = "a1b2bc3", lastModified = "2023-12-31T23:59:59+01:00"),
            hostName = HOST_NAME
        )

    private fun createFetchFailure(httpStatus: HttpStatus, isUserRequest: Boolean) =
        FetchFailure(
            httpStatus,
            hostName = HOST_NAME,
            exceptionMessage = "",
            isUserRequest = isUserRequest
        )

    private fun AppRepository.loadSchedule(
        isUserRequest: Boolean,
        onFetchingDone: OnFetchingDone = {},
        onParsingDone: OnParsingDone = {},
    ) = loadSchedule(HOST_NAME, isUserRequest, onFetchingDone, onParsingDone)

}
