package nerd.tuxmobil.fahrplan.congress.sponsors

sealed interface SponsorsViewEvent {
    data object OnBackClick : SponsorsViewEvent
    data class OnSponsorUrlClick(val url: String) : SponsorsViewEvent
}
