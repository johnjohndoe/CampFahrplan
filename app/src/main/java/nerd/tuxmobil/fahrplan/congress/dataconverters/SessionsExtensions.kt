package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.android.eventfahrplan.database.models.Meta
import info.metadude.kotlin.library.droidconberlin.models.Session
import org.threeten.bp.LocalDate
import org.threeten.bp.Period

fun List<Session>.toMetaDatabaseModel() = Meta(
        numDays = sortedDates.daysCount,
        title = "Droidcon Berlin, 25.-27.06.2018"
)

fun List<Session>.toLecturesDatabaseModel() = map { it.toLectureDatabaseModel(sortedDates) }

private val List<Session>.sortedDates: List<LocalDate>
    get() {
        val dates = mutableSetOf<LocalDate>()
        forEach { dates.add(it.start_iso[0].toLocalDate) }
        return dates.sorted()
    }

private val List<LocalDate>.daysCount
    get() = Period.between(first(), last()).days + 1
