package nerd.tuxmobil.fahrplan.congress.sponsors.models

import androidx.annotation.DrawableRes

data class Sponsor(

    val category: Category,
    @DrawableRes val imageUrl: Int,
    val description: String,
    val url: String

)
