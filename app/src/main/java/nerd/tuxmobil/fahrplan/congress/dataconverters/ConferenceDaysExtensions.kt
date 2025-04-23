package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import info.metadude.android.eventfahrplan.network.models.Session as SessionNetworkModel

fun List<ConferenceDay>.toSessionsNetworkModel(): List<SessionNetworkModel> =
    flatMap { it.toSessionsNetworkModel(sortedDates) }.toList()

private val List<ConferenceDay>.sortedDates
    get() = mapNotNull { it.date }.sorted()
