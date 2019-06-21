package nerd.tuxmobil.fahrplan.congress.utils

import nerd.tuxmobil.fahrplan.congress.BuildConfig.EVENT_URL
import nerd.tuxmobil.fahrplan.congress.models.Lecture as Event

class EventUrlComposer(event: Event) {

    fun getEventUrl(): String = EVENT_URL

}
