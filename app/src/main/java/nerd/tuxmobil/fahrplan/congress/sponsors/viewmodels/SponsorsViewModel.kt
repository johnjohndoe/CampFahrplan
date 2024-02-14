package nerd.tuxmobil.fahrplan.congress.sponsors.viewmodels

import androidx.lifecycle.ViewModel
import nerd.tuxmobil.fahrplan.congress.sponsors.factories.SponsorsFactory

class SponsorsViewModel : ViewModel() {

    fun loadSponsors() = SponsorsFactory.createSponsors()

}
