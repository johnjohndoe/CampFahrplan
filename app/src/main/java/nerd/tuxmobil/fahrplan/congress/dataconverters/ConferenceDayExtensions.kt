package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import org.threeten.bp.LocalDate

fun ConferenceDay.toSessionsAppModel(sortedDates: List<LocalDate>) =
    rooms.flatMap { it.sessions }
        .filterNot { "Break" == it.title.trim() }
        .map { it.toSessionAppModel(sortedDates) }
        .toList()

val ConferenceDay.dateLocalDate: LocalDate
    get() = date.toLocalDate()
