package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.android.eventfahrplan.database.models.Lecture
import info.metadude.kotlin.library.droidconberlin.models.Session
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.ChronoField

fun Session.toLectureDatabaseModel(sortedDates: List<LocalDate>) = Lecture(
        eventId = nid,
        abstractt = "",
        date = startsAtLocalDateString,
        dateUTC = startsAtUtc,
        dayIndex = oneBasedDayIndex(sortedDates, firstStartsAt.toLocalDate),
        description = abstract,
        duration = duration, // minutes
        hasAlarm = false,
        language = languageAbbreviation,
        links = "",
        isHighlight = false,
        recordingLicense = "",
        recordingOptOut = false,
        relativeStartTime = minuteOfDay,
        room = roomNames,
        roomIndex = firstRoomId,
        slug = uri,
        speakers = speakerNames,
        startTime = minuteOfDay, // minutes since day start
        subtitle = "",
        title = sanitizedTitle,
        track = track,
        type = type,

        changedDay = false,
        changedDuration = false,
        changedIsCanceled = false,
        changedIsNew = false,
        changedLanguage = false,
        changedRecordingOptOut = false,
        changedRoom = false,
        changedSpeakers = false,
        changedSubtitle = false,
        changedTime = false,
        changedTitle = false,
        changedTrack = false
)

val Session.track: String
    get() {
        when (category) {
            "Android Architecture" -> return "Architecture"
            "Android Game Development &amp; Virtual Reality" -> return "Games / VR"
            "APIs" -> return "APIs"
            "Best Practices / Code Quality" -> return "Best Practices"
            "Cross-Plattform Development" -> return "X-Platform"
            "Design / UI/UX" -> return "UI/UX"
            "Enterprise" -> return "Enterprise"
            "Internet of Things" -> return "IOT"
            "Languages" -> return "Languages"
            "Open Source and Research" -> return "Open Source"
        }
        return category
    }

private fun oneBasedDayIndex(eventDates: List<LocalDate>, date: LocalDate) =
        eventDates.indexOfFirst { it == date } + 1

private val Session.duration: Int
    get() = Duration.between(firstStartsAt, firstEndsAt).toMinutes().toInt()

private val Session.firstStartsAt
    get() = start_iso[0]

private val Session.firstEndsAt
    get() = end_iso[0]

private val Session.firstRoomId
    get() = room_id[0].toInt()

private val Session.languageAbbreviation
    get() = language.toLowerCase().substring(0, 2)

private val Session.minuteOfDay
    get() = firstStartsAt.get(ChronoField.MINUTE_OF_DAY)

private val Session.roomNames
    get() = room.joinToString()

private val Session.speakerNames
    get() = speaker_names.joinToString()

private val Session.startsAtUtc
    get() = firstStartsAt.toEpochSecond() * 1000L

private val Session.startsAtLocalDateString
    get() = firstStartsAt.toLocalDate.toString()

private val Session.sanitizedTitle
    get() = title.trim().replace("&#039;", "'")

val OffsetDateTime.toLocalDate: LocalDate
    get() = toLocalDate()
