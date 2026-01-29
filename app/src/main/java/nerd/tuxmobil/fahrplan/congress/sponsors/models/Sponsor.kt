package nerd.tuxmobil.fahrplan.congress.sponsors.models

import androidx.annotation.DrawableRes

data class Sponsor(

    @get:DrawableRes val imageUrl: Int,
    val description: String,
    val url: String

)
