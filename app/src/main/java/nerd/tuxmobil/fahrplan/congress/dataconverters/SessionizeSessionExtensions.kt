package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.android.eventfahrplan.commons.logging.Logging
import nerd.tuxmobil.fahrplan.congress.BuildConfig
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.TextStyle
import org.threeten.bp.temporal.ChronoField
import java.util.Locale
import info.metadude.android.eventfahrplan.network.models.Session as SessionNetworkModel
import info.metadude.kotlin.library.sessionize.gridtable.models.Session as SessionizeSession

// Requires to filter out sessions with null properties upfront.
fun SessionizeSession.toSessionNetworkModel(sortedDates: List<ZonedDateTime>): SessionNetworkModel {
    val oneBasedDayIndex = oneBasedDayIndex(sortedDates, startsAt!!)
    require(oneBasedDayIndex > 0) { "Invalid dayIndex=$oneBasedDayIndex for session: $id" }
    return SessionNetworkModel(
        sessionId = id,
        dateText = startsAtLocalDateString,
        dateUTC = dateUtcMs,
        dayIndex = oneBasedDayIndex,
        description = descriptionText,
        duration = durationValue,
        relativeStartTime = minuteOfDay,
        roomName = roomName,
        speakers = speakersNames.ifEmpty { "" },
        startTime = minuteOfDay,
        title = titleText,
        timeZoneOffset = startsAt!!.offset.totalSeconds,
        track = trackName(oneBasedDayIndex),
        url = "", // Hide "Event online" since final URL scheme is unknown; otherwise: urlText
    )
}

private fun oneBasedDayIndex(eventDates: List<ZonedDateTime>, date: ZonedDateTime): Int {
    return eventDates.indexOfFirst { it.dayOfYear == date.dayOfYear } + 1
}

private val SessionizeSession.dateUtcMs
    get() = startsAt!!.toEpochSecond().milliseconds

private val SessionizeSession.descriptionText
    get() = description.orEmpty()

private val SessionizeSession.durationValue
    get() = Duration.between(startsAt, endsAt).toMinutes().toInt()

private val SessionizeSession.minuteOfDay
    get() = startsAt!!.get(ChronoField.MINUTE_OF_DAY)

private val SessionizeSession.roomName
    get() = room.trim()

private val SessionizeSession.speakersNames
    get() = speakers.joinToString { it.name }

private val SessionizeSession.startsAtLocalDate: LocalDate
    get() = startsAt!!.toLocalDate()

private val SessionizeSession.startsAtLocalDateString
    get() = startsAtLocalDate.toString()

private val SessionizeSession.titleText
    get() = title.trim()

val SessionizeSession.urlText: String
    get() = if (isConferenceSession || isInterviewSession) {
        BuildConfig.EVENT_URL
    } else {
        buildString {
            append(BuildConfig.EVENT_URL)
            val day = startsAt!!.dayOfMonth
            val month = startsAt!!.month.getDisplayName(TextStyle.FULL, Locale.US).lowercase()
            append("$month-$day/$id")
        }
    }


private fun SessionizeSession.trackName(dayIndex: Int): String {
    return when {
        isConferenceSession -> "Conference"
        isHandsOnSession && dayIndex > 1 -> "Hands On"
        isInterviewSession -> "Interviews"
        else -> categories
                .filter { it.name != "Tags" }
                .flatMap { it.categoryItems }
                .joinToString { it.name.trim() }
                .replace("Introductory and overview", "Introductory")
                .trim()
    }
}

private val SessionizeSession.isConferenceSession
    get() = "Lunch".equals(titleText, ignoreCase = true) ||
            "Breakfast".equals(titleText, ignoreCase = true) ||
            "Coffee Break".equals(titleText, ignoreCase = true) ||
            titleText.endsWith("Party", ignoreCase = false) ||
            titleText.contains("Registration", ignoreCase = true)

private val SessionizeSession.isHandsOnSession
    get() = roomName.contains("Hands On", ignoreCase = true)

private val SessionizeSession.isInterviewSession
    get() = roomName.contains("Interviews", ignoreCase = true)

private val Long.milliseconds
    get() = this * 1000
