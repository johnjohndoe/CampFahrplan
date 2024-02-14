package nerd.tuxmobil.fahrplan.congress.sponsors.models

import nerd.tuxmobil.fahrplan.congress.R

enum class Category(

    val title: Int

) {

    BRONZE(R.string.sponsor_category_bronze),
    GOLD(R.string.sponsor_category_gold),
    PLATINUM(R.string.sponsor_category_platinum),
    SILVER(R.string.sponsor_category_silver),
    MEDIA_PARTNER(R.string.sponsor_category_media_partners),

}
