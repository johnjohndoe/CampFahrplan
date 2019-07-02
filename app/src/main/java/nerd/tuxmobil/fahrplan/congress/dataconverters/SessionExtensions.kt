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
    day = oneBasedDayIndex(sortedDates, startsAtLocalDate)
    description = descriptionText
    duration = durationValue
    relStartTime = minuteOfDay
    room = roomName
    speakers = speakersNames
    startTime = minuteOfDay
    title = titleText
    track = trackName
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
    get() = room

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

private val Session.titleSanitized: String
    get() = title.toLowerCase()

private val Session.trackName: String
    get() = when {
        titleSanitized == "lunch" -> "Break"
        titleSanitized.contains("keynote") -> "Keynote"
        titleSanitized.startsWith("welcome and") -> "Keynote"
        titleSanitized.startsWith("designing for sustainable") -> "Keynote"
        titleSanitized.contains("closed session") -> "Orga-Team"
        titleSanitized.contains("general party") -> "Party"
        titleSanitized.contains("registration") -> "Registration"
        titleSanitized.contains("min") && titleSanitized.endsWith("session") -> "tba."
        roomName.trim() == "Google Booth" -> "Google Booth"
        else -> categories
                .filter { it.name != "Tags" }
                .flatMap { it.categoryItems }
                .joinToString { it.name }
    }

private val Long.milliseconds
    get() = this * 1000

private const val TIME_ZONE_OFFSET = 2L
