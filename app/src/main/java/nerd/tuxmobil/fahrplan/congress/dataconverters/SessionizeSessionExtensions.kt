package nerd.tuxmobil.fahrplan.congress.dataconverters

import nerd.tuxmobil.fahrplan.congress.BuildConfig
import info.metadude.kotlin.library.sessionize.gridtable.models.Session as SessionizeSession
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.TextStyle
import org.threeten.bp.temporal.ChronoField
import java.util.Locale
import nerd.tuxmobil.fahrplan.congress.models.Session as SessionAppModel

fun SessionizeSession.toSessionAppModel(sortedDates: List<LocalDate>) = SessionAppModel(idHash).apply {
    date = startsAtLocalDateString
    dateUTC = dateUtcMs
    val oneBasedDayIndex = oneBasedDayIndex(sortedDates, startsAtLocalDate)
    day = oneBasedDayIndex
    description = descriptionText
    duration = durationValue
    relStartTime = minuteOfDay
    room = roomName
    speakers = listOf(speakersNames)
    startTime = minuteOfDay
    title = titleText
    track = trackName(oneBasedDayIndex)
    url = "" // Hide "Event online" since final URL scheme is unknown; otherwise: urlText
}

private fun oneBasedDayIndex(eventDates: List<LocalDate>, date: LocalDate): Int {
    return eventDates.indexOfFirst { it == date } + 1
}

private val SessionizeSession.dateUtcMs
    get() = startsAtUtc.toEpochSecond(ZoneOffset.UTC).milliseconds

private val SessionizeSession.descriptionText
    get() = description.orEmpty()

private val SessionizeSession.durationValue
    get() = Duration.between(startsAtUtc, endsAtUtc).toMinutes().toInt()

private val SessionizeSession.endsAtUtc
    get() = endsAt.minusHours(TIME_ZONE_OFFSET)

private val SessionizeSession.idHash
    get() = "${id.hashCode()}"

private val SessionizeSession.minuteOfDay
    get() = startsAtUtc.get(ChronoField.MINUTE_OF_DAY)

private val SessionizeSession.roomName
    get() = room.trim()

private val SessionizeSession.speakersNames
    get() = speakers.joinToString { it.name }

private val SessionizeSession.startsAtLocalDate: LocalDate
    get() = startsAtUtc.toLocalDate()

private val SessionizeSession.startsAtLocalDateString
    get() = startsAtLocalDate.toString()

private val SessionizeSession.startsAtUtc
    get() = startsAt.minusHours(TIME_ZONE_OFFSET)

private val SessionizeSession.titleText
    get() = title.trim()

val SessionizeSession.urlText: String
    get() = if (isConferenceSession || isInterviewSession) {
        BuildConfig.EVENT_URL
    } else {
        buildString {
            append(BuildConfig.EVENT_URL)
            val day = startsAt.dayOfMonth
            val month = startsAt.month.getDisplayName(TextStyle.FULL, Locale.US).lowercase()
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
                .joinToString { it.name }
                .replace("Introductory and overview", "Introductory")
    }
}

private val SessionizeSession.isConferenceSession
    get() = "Lunch".equals(titleText, ignoreCase = true) ||
            "Breakfast".equals(titleText, ignoreCase = true) ||
            titleText.endsWith("Party", ignoreCase = false) ||
            titleText.contains("Registration", ignoreCase = true)

private val SessionizeSession.isHandsOnSession
    get() = roomName.contains("Hands On", ignoreCase = true)

private val SessionizeSession.isInterviewSession
    get() = roomName.contains("Interviews", ignoreCase = true)


private val Long.milliseconds
    get() = this * 1000

private const val TIME_ZONE_OFFSET = 2L
