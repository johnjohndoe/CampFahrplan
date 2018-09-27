package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import nerd.tuxmobil.fahrplan.congress.models.Lecture as EventAppModel
import nerd.tuxmobil.fahrplan.congress.models.Meta as MetaAppModel

fun List<ConferenceDay>.toEventAppModels(): List<EventAppModel> =
        flatMap { it.toEventAppModels(sortedDates) }.toList()


fun List<ConferenceDay>.toMetaAppModel() = MetaAppModel(
        numDays = size
)

private val List<ConferenceDay>.sortedDates
    get() = map { it.dateLocalDate }.sorted()
