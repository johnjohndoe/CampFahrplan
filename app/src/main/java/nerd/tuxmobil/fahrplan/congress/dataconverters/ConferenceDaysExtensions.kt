package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import nerd.tuxmobil.fahrplan.congress.models.Session as SessionAppModel

fun List<ConferenceDay>.toSessionsAppModel(): List<SessionAppModel> =
    flatMap { it.toSessionsAppModel(sortedDates) }.toList()

private val List<ConferenceDay>.sortedDates
    get() = map { it.dateLocalDate }.sorted()
