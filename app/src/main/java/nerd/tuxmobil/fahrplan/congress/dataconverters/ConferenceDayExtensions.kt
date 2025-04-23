package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

fun ConferenceDay.toSessionsNetworkModel(sortedDates: List<ZonedDateTime>) =
    rooms.flatMap { it.sessions }
        .filterNot { "Break" == it.title.trim() }
        .filter { it.startsAt != null && it.endsAt != null }
        .map { it.toSessionNetworkModel(sortedDates) }
        .toList()
