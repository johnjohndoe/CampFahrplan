package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.kotlin.library.sessionize.gridtable.models.Session
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset
import org.threeten.bp.temporal.ChronoField
import nerd.tuxmobil.fahrplan.congress.models.Lecture as EventAppModel

fun Session.toEventAppModel(sortedDates: List<LocalDate>) = EventAppModel(idHash).apply {
    date = startsAtLocalDateString
    dateUTC = dateUtcMs
    val oneBasedDayIndex = oneBasedDayIndex(sortedDates, startsAtLocalDate)
    day = oneBasedDayIndex
    description = descriptionText
    duration = durationValue
    relStartTime = minuteOfDay
    room = roomName
    speakers = speakersNames
    startTime = minuteOfDay
    title = titleText
    track = trackName(oneBasedDayIndex)
    url = ""
}

private fun oneBasedDayIndex(eventDates: List<LocalDate>, date: LocalDate): Int {
    return eventDates.indexOfFirst { it == date } + 1
}

private val Session.dateUtcMs
    get() = startsAtUtc.toEpochSecond(ZoneOffset.UTC).milliseconds

private val Session.descriptionText
    get() = description ?: ""

private val Session.durationValue
    get() = Duration.between(startsAtUtc, endsAtUtc).toMinutes().toInt()

private val Session.endsAtUtc
    get() = endsAt.minusHours(TIME_ZONE_OFFSET)

private val Session.idHash
    get() = "${id.hashCode()}"

private val Session.minuteOfDay
    get() = startsAtUtc.get(ChronoField.MINUTE_OF_DAY)

private val Session.roomName
    get() = room.trim()

private val Session.speakersNames
    get() = speakers.joinToString { it.name }

private val Session.startsAtLocalDate: LocalDate
    get() = startsAtUtc.toLocalDate()

private val Session.startsAtLocalDateString
    get() = startsAtLocalDate.toString()

private val Session.startsAtUtc
    get() = startsAt.minusHours(TIME_ZONE_OFFSET)

private val Session.titleText
    get() = title.trim()

private fun Session.trackName(dayIndex: Int): String {
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

private val Session.isConferenceSession
    get() = "Lunch".equals(titleText, ignoreCase = true) ||
            "Party".equals(titleText, ignoreCase = true) ||
            titleText.contains("Registration", ignoreCase = true)

private val Session.isHandsOnSession
    get() = roomName.contains("Hands On", ignoreCase = true)

private val Session.isInterviewSession
    get() = roomName.contains("Interviews", ignoreCase = true)

private val Long.milliseconds
    get() = this * 1000

private const val TIME_ZONE_OFFSET = 1L