package nerd.tuxmobil.fahrplan.congress.sponsors.factories

import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.BRONZE
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.GOLD
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.MEDIA_PARTNER
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.PLATINUM
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.SILVER
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor

object SponsorsFactory {

    fun create() = listOf(
        PLATINUM to Sponsor(R.drawable.sponsor_001, "WhereGroup", "https://fossgis-konferenz.de/2026/sponsor/001_wheregroup.php"),
        PLATINUM to Sponsor(R.drawable.sponsor_002, "opengis", "https://fossgis-konferenz.de/2026/sponsor/002_OPENGis_CH.php"),
        PLATINUM to Sponsor(R.drawable.sponsor_003, "Dataport", "https://fossgis-konferenz.de/2026/sponsor/003_dataport.php"),

        GOLD to Sponsor(R.drawable.sponsor_101, "BKG", "https://fossgis-konferenz.de/2026/sponsor/101_bkg.php"),

        SILVER to Sponsor(R.drawable.sponsor_201, "terrestris mundialis", "https://fossgis-konferenz.de/2026/sponsor/201_terrestris_mundialis.php"),
        SILVER to Sponsor(R.drawable.sponsor_202, "camptocamp", "https://fossgis-konferenz.de/2026/sponsor/202_camp2camp.php"),
        SILVER to Sponsor(R.drawable.sponsor_203, "LGV", "https://fossgis-konferenz.de/2026/sponsor/203_LGV.php"),
        SILVER to Sponsor(R.drawable.sponsor_204, "sourcepole", "https://fossgis-konferenz.de/2026/sponsor/204_sourcepole.php"),
        SILVER to Sponsor(R.drawable.sponsor_205, "Lutra Consulting ltd.", "https://fossgis-konferenz.de/2026/sponsor/205_lutraconsulting.php"),
        SILVER to Sponsor(R.drawable.sponsor_206, "geoSYS", "https://fossgis-konferenz.de/2026/sponsor/206_geoSYS.php"),
        SILVER to Sponsor(R.drawable.sponsor_207, "Geoinformatikbüro Dassau", "https://fossgis-konferenz.de/2026/sponsor/207_gbd.php"),
        SILVER to Sponsor(R.drawable.sponsor_208, "GIS-Consult", "https://fossgis-konferenz.de/2026/sponsor/208_GIS-Consult.php"),
        SILVER to Sponsor(R.drawable.sponsor_209, "Feelgood Geosolutions", "https://fossgis-konferenz.de/2026/sponsor/209_Feelgood.php"),

        BRONZE to Sponsor(R.drawable.sponsor_301, "latlon", "https://fossgis-konferenz.de/2026/sponsor/301_latlon.php"),
        BRONZE to Sponsor(R.drawable.sponsor_302, "Geofabrik GmbH", "https://fossgis-konferenz.de/2026/sponsor/302_geofabrik.php"),
        BRONZE to Sponsor(R.drawable.sponsor_303, "TomTom", "https://fossgis-konferenz.de/2026/sponsor/303_TomTom.php"),
        BRONZE to Sponsor(R.drawable.sponsor_304, "Adesso SE", "https://fossgis-konferenz.de/2026/sponsor/304_Adesso.php"),
        BRONZE to Sponsor(R.drawable.sponsor_305, "geOps AG", "https://fossgis-konferenz.de/2026/sponsor/305_geOps.php"),
        BRONZE to Sponsor(R.drawable.sponsor_306, "MDPI AG", "https://fossgis-konferenz.de/2026/sponsor/306_MDPI-AG.php"),
        BRONZE to Sponsor(R.drawable.sponsor_307, "grit GmbH ", "https://fossgis-konferenz.de/2026/sponsor/307_grit.php"),
        BRONZE to Sponsor(R.drawable.sponsor_308, "DVZ-MV GmbH ", "https://fossgis-konferenz.de/2026/sponsor/308_dvz-mv.php"),
        BRONZE to Sponsor(R.drawable.sponsor_309, "NRW.Mobidrom", "https://fossgis-konferenz.de/2026/sponsor/309_NRW-Mobidrom.php"),

        MEDIA_PARTNER to Sponsor(R.drawable.sponsor_501, "OSGeoLive", "https://live.osgeo.org/de/index.html"),
        MEDIA_PARTNER to Sponsor(R.drawable.sponsor_502, "C3VOC", "https://c3voc.de/events/"),
        MEDIA_PARTNER to Sponsor(R.drawable.sponsor_503, "TIB", "https://av.tib.eu/search?f=publisher%3Bhttp://av.tib.eu/resource/FOSSGIS_e.V."),
        MEDIA_PARTNER to Sponsor(R.drawable.sponsor_504, "O'Reilly", "https://www.oreilly.de/"),
        MEDIA_PARTNER to Sponsor(R.drawable.sponsor_505, "GoGeoGo", "https://www.gogeogo.com/de"),
    )

}
