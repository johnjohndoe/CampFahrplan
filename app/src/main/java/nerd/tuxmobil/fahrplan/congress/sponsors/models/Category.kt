package nerd.tuxmobil.fahrplan.congress.sponsors.models

import nerd.tuxmobil.fahrplan.congress.R

enum class Category(

    val title: Int

) {

    BRONZE(R.string.sponsor_category_bronze),
    DIAMOND(R.string.sponsor_category_diamond),
    GOLD(R.string.sponsor_category_gold),
    ORGANIZERS(R.string.sponsor_category_organizers),
    ORGANIZING_PARTNERS(R.string.sponsor_category_organizing_partners),
    PARTNERS(R.string.sponsor_category_partners),
    PLATINUM(R.string.sponsor_category_platinum),
    SILVER(R.string.sponsor_category_silver),
    SUPPORTERS(R.string.sponsor_category_supporters),

}
