package nerd.tuxmobil.fahrplan.congress.sponsors.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsEffect
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsEffect.NavigateBack
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsEffect.OpenSponsorUrl
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent.OnBackClick
import nerd.tuxmobil.fahrplan.congress.sponsors.SponsorsViewEvent.OnSponsorUrlClick
import nerd.tuxmobil.fahrplan.congress.sponsors.factories.SponsorsFactory

class SponsorsViewModel : ViewModel() {

    private val mutableEffects = Channel<SponsorsEffect>()
    val effects = mutableEffects.receiveAsFlow()

    val sponsorByCategory = SponsorsFactory.create()

    fun onViewEvent(event: SponsorsViewEvent) {
        when (event) {
            is OnBackClick -> sendEffect(NavigateBack)
            is OnSponsorUrlClick -> sendEffect(OpenSponsorUrl(event.url))
        }
    }

    private fun sendEffect(effect: SponsorsEffect) {
        viewModelScope.launch {
            mutableEffects.send(effect)
        }
    }
}
