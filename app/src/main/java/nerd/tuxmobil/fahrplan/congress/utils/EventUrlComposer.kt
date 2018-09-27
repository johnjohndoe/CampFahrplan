package nerd.tuxmobil.fahrplan.congress.utils

import nerd.tuxmobil.fahrplan.congress.BuildConfig.EVENT_URL
import nerd.tuxmobil.fahrplan.congress.BuildConfig.SERVER_BACKEND_TYPE
import nerd.tuxmobil.fahrplan.congress.utils.ServerBackendType.*
import nerd.tuxmobil.fahrplan.congress.models.Lecture as Event

class EventUrlComposer(private val event: Event) {

    fun getEventUrl(): String = EVENT_URL

}
