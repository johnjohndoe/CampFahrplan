package nerd.tuxmobil.fahrplan.congress.sponsors.factories

import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.BRONZE
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.GOLD
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.PLATINUM
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.SILVER
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor

object SponsorsFactory {

    fun createSponsors() = listOf(
        Sponsor(PLATINUM, R.drawable.sponsor_camptocamp, "camp to camp", "https://www.camptocamp.com"),
        Sponsor(PLATINUM, R.drawable.sponsor_wheregroup, "WhereGroup", "https://www.wheregroup.com"),

        Sponsor(GOLD, R.drawable.sponsor_bkg, "BKG", "https://www.bkg.bund.de"),
        Sponsor(GOLD, R.drawable.sponsor_maptiler, "maptiler", "https://maptiler.ch"),

        Sponsor(SILVER, R.drawable.sponsor_terrestris, "terrestris", "https://www.terrestris.de"),
        Sponsor(SILVER, R.drawable.sponsor_geops, "geOps", "https://geops.ch"),
        Sponsor(SILVER, R.drawable.sponsor_geoinfo, "Geoinfo", "https://www.geoinfo.ch"),
        Sponsor(SILVER, R.drawable.sponsor_nti_cwsm, "SAGis", "https://www.sagisweb.de"),

        Sponsor(BRONZE, R.drawable.sponsor_mundialis, "mundialis", "https://www.mundialis.de"),
        Sponsor(BRONZE, R.drawable.sponsor_mapwebbing, "mapwebbing", "http://www.lingner.eu"),
        Sponsor(BRONZE, R.drawable.sponsor_komoot, "komoot", "https://www.komoot.com"),
        Sponsor(BRONZE, R.drawable.sponsor_gbd, "GBD", "https://www.gbd-consult.de"),
        Sponsor(BRONZE, R.drawable.sponsor_latlon, "latlon", "https://www.lat-lon.de"),
        Sponsor(BRONZE, R.drawable.sponsor_esri_suisse, "esri Suisse", "https://www.esri.ch"),
        Sponsor(BRONZE, R.drawable.sponsor_sourcepole, "sourcepole", "https://www.sourcepole.ch"),
        Sponsor(BRONZE, R.drawable.sponsor_yeymaps, "yey maps", "https://www.yeymaps.de"),
        Sponsor(BRONZE, R.drawable.sponsor_opencage, "OpenCage", "https://opencagedata.com"),
        Sponsor(BRONZE, R.drawable.sponsor_dvz_mv, "DVZ Mecklenburg-Vorpommern", "https://www.dvz-mv.de"),
        Sponsor(BRONZE, R.drawable.sponsor_gkg, "GKG", "http://www.gkg-kassel.de"),
    )

}
