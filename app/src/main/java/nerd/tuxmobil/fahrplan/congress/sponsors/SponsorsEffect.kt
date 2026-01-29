package nerd.tuxmobil.fahrplan.congress.sponsors

interface SponsorsEffect {
    data object NavigateBack : SponsorsEffect
    data class OpenSponsorUrl(val url: String) : SponsorsEffect
}
