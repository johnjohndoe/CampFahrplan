package nerd.tuxmobil.fahrplan.congress.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import info.metadude.android.eventfahrplan.commons.logging.Logging
import info.metadude.android.eventfahrplan.commons.temporal.DateFormatter
import info.metadude.android.eventfahrplan.commons.temporal.Moment
import nerd.tuxmobil.fahrplan.congress.alarms.AlarmReceiver
import nerd.tuxmobil.fahrplan.congress.alarms.AlarmUpdater
import nerd.tuxmobil.fahrplan.congress.extensions.getAlarmManager
import nerd.tuxmobil.fahrplan.congress.models.ConferenceTimeFrame
import nerd.tuxmobil.fahrplan.congress.models.DateInfo
import nerd.tuxmobil.fahrplan.congress.models.DateInfos
import nerd.tuxmobil.fahrplan.congress.utils.PendingIntentCompat.FLAG_IMMUTABLE


object FahrplanMisc {

    private const val LOG_TAG = "FahrplanMisc"

    /**
     * Returns a [DateInfos] instance composed from the given [dateInfos] list.
     * Duplicate [DateInfo] entries are removed.
     */
    fun createDateInfos(dateInfos: List<DateInfo>): DateInfos {
        val infos = DateInfos()
        for (dateInfo in dateInfos) {
            if (dateInfo !in infos) {
                infos.add(dateInfo)
            }
        }
        return infos
    }

    fun setUpdateAlarm(context: Context, conferenceTimeFrame: ConferenceTimeFrame, isInitial: Boolean, logging: Logging): Long {
        val alarmManager = context.getAlarmManager()
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
            .apply { action = AlarmReceiver.ALARM_UPDATE }
        val pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, FLAG_IMMUTABLE)

        logging.d(LOG_TAG, "set update alarm")
        val now = Moment.now()

        return AlarmUpdater(conferenceTimeFrame, object : AlarmUpdater.OnAlarmUpdateListener {

            override fun onCancelUpdateAlarm() {
                logging.d(LOG_TAG, "Canceling alarm.")
                alarmManager.cancel(pendingIntent)
            }

            override fun onScheduleUpdateAlarm(interval: Long, nextFetch: Moment) {
                val next = nextFetch.minusMilliseconds(now.toMilliseconds()).toMilliseconds()
                val nextDateTime = DateFormatter.newInstance(useDeviceTimeZone = false)
                    .getFormattedDateTimeLong(nextFetch.toMilliseconds(), sessionZoneOffset = null)
                logging.d(LOG_TAG, "Scheduling update alarm to interval $interval, next in ~$next ms, at $nextDateTime")
                // Redesign might be needed as of Android 12 (API level 31)
                // See https://developer.android.com/training/scheduling/alarms
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, nextFetch.toMilliseconds(), interval, pendingIntent)
            }

        }).calculateInterval(now, isInitial)
    }

}
