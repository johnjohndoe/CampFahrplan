package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import org.threeten.bp.LocalDate
import nerd.tuxmobil.fahrplan.congress.models.Lecture as EventAppModel

fun ConferenceDay.toEventAppModels(sortedDates: List<LocalDate>) =
        rooms.flatMap { it.sessions }
                .map { it.toEventAppModel(sortedDates) }
                .toList()

val ConferenceDay.dateLocalDate: LocalDate
    get() = date.toLocalDate()
