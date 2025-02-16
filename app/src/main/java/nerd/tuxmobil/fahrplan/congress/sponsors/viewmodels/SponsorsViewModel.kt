package nerd.tuxmobil.fahrplan.congress.sponsors.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent.OnBackClick
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent.OnSponsorUrlClick
import nerd.tuxmobil.fahrplan.congress.sponsors.factories.SponsorsFactory

class SponsorsViewModel : ViewModel() {

    private val mutableNavigateBack = Channel<Unit>()
    val navigateBack = mutableNavigateBack.receiveAsFlow()

    private val mutableShowSponsorWebsite = Channel<String>()
    val showSponsorWebsite = mutableShowSponsorWebsite.receiveAsFlow()

    val sponsors = SponsorsFactory.createSponsors()

    fun onViewEvent(event: SponsorsViewEvent) {
        when (event) {
            is OnBackClick -> mutableNavigateBack.sendOneTimeEvent(Unit)
            is OnSponsorUrlClick -> mutableShowSponsorWebsite.sendOneTimeEvent(event.url)
        }
    }

    private fun <E> SendChannel<E>.sendOneTimeEvent(event: E) {
        viewModelScope.launch {
            send(event)
        }
    }

}
