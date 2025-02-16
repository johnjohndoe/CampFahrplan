package nerd.tuxmobil.fahrplan.congress.sponsors.factories

import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.BRONZE
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.GOLD
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.MEDIA_PARTNER
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.PLATINUM
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.SILVER
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor

object SponsorsFactory {

    fun createSponsors() = listOf(
        Sponsor(PLATINUM, R.drawable.sponsor_001, "opengis", "https://fossgis-konferenz.de/2025/sponsor/001_OPENGis_CH.php"),
        Sponsor(PLATINUM, R.drawable.sponsor_002, "WhereGroup", "https://fossgis-konferenz.de/2025/sponsor/002_wheregroup.php"),
        Sponsor(PLATINUM, R.drawable.sponsor_003, "Dataport", "https://fossgis-konferenz.de/2025/sponsor/003_dataport.php"),

        Sponsor(GOLD, R.drawable.sponsor_101, "camptocamp", "https://fossgis-konferenz.de/2025/sponsor/101_camp2camp.php"),
        Sponsor(GOLD, R.drawable.sponsor_102, "BKG", "https://fossgis-konferenz.de/2025/sponsor/102_bkg.php"),
        Sponsor(GOLD, R.drawable.sponsor_103, "GIS-Consult", "https://fossgis-konferenz.de/2025/sponsor/103_GIS-Consult.php"),
        Sponsor(GOLD, R.drawable.sponsor_104, "NRW.Mobidrom", "https://fossgis-konferenz.de/2025/sponsor/104_NRW-Mobidrom.php"),
        Sponsor(GOLD, R.drawable.sponsor_105, "openSenseLab", "https://fossgis-konferenz.de/2025/sponsor/105_openSenseLab.php"),

        Sponsor(SILVER, R.drawable.sponsor_201, "CONET ISB GmbH", "https://fossgis-konferenz.de/2025/sponsor/201_CONET-ISB.php"),
        Sponsor(SILVER, R.drawable.sponsor_202, "EFTAS", "https://fossgis-konferenz.de/2025/sponsor/202_EFTAS.php"),
        Sponsor(SILVER, R.drawable.sponsor_203, "Adesso SE", "https://fossgis-konferenz.de/2025/sponsor/203_Adesso.php"),

        Sponsor(BRONZE, R.drawable.sponsor_401, "terrestris", "https://fossgis-konferenz.de/2025/sponsor/401_terrestris.php"),
        Sponsor(BRONZE, R.drawable.sponsor_402, "mundialis", "https://fossgis-konferenz.de/2025/sponsor/402_mundialis.php"),
        Sponsor(BRONZE, R.drawable.sponsor_403, "grit GmbH ", "https://fossgis-konferenz.de/2025/sponsor/403_grit.php"),
        Sponsor(BRONZE, R.drawable.sponsor_404, "OpenCage", "https://fossgis-konferenz.de/2025/sponsor/404_opencage.php"),
        Sponsor(BRONZE, R.drawable.sponsor_405, "Geoinformatikbüro Dassau", "https://fossgis-konferenz.de/2025/sponsor/405_gbd.php"),
        Sponsor(BRONZE, R.drawable.sponsor_406, "geofabrik", "https://fossgis-konferenz.de/2025/sponsor/406_geofabrik.php"),
        Sponsor(BRONZE, R.drawable.sponsor_407, "geoSYS", "https://fossgis-konferenz.de/2025/sponsor/407_geoSYS.php"),
        Sponsor(BRONZE, R.drawable.sponsor_408, "Lutra Consulting ltd.", "https://fossgis-konferenz.de/2025/sponsor/408_lutraconsulting.php"),
        Sponsor(BRONZE, R.drawable.sponsor_409, "52°North Spatial Information Research GmbH", "https://fossgis-konferenz.de/2025/sponsor/409_52north.php"),
        Sponsor(BRONZE, R.drawable.sponsor_410, "komoot", "https://fossgis-konferenz.de/2025/sponsor/410_komoot.php"),
        Sponsor(BRONZE, R.drawable.sponsor_411, "latlon", "https://fossgis-konferenz.de/2025/sponsor/411_latlon.php"),
        Sponsor(BRONZE, R.drawable.sponsor_412, "TomTom Global Content BV", "https://fossgis-konferenz.de/2025/sponsor/412_TomTom.php"),
        Sponsor(BRONZE, R.drawable.sponsor_413, "FeelGood", "https://fossgis-konferenz.de/2025/sponsor/413_Feelgood.php"),
        Sponsor(BRONZE, R.drawable.sponsor_414, "GeoCockpit UG", "https://fossgis-konferenz.de/2025/sponsor/414_geocockpit.php"),
        Sponsor(BRONZE, R.drawable.sponsor_415, "GISCAD", "https://fossgis-konferenz.de/2025/sponsor/415_GISCAD.php"),
        Sponsor(BRONZE, R.drawable.sponsor_416, "sourcepole", "https://fossgis-konferenz.de/2025/sponsor/416_sourcepole.php"),
        Sponsor(BRONZE, R.drawable.sponsor_417, "gkg", "https://fossgis-konferenz.de/2025/sponsor/417_gkg.php"),

        Sponsor(MEDIA_PARTNER, R.drawable.sponsor_501, "OSGeoLive", "https://live.osgeo.org/de/index.html"),
        Sponsor(MEDIA_PARTNER, R.drawable.sponsor_502, "C3VOC", "https://c3voc.de/events/"),
        Sponsor(MEDIA_PARTNER, R.drawable.sponsor_503, "Technische Informationsbibliothek (TIB)", "https://av.tib.eu/search?f=publisher%3Bhttp://av.tib.eu/resource/FOSSGIS_e.V./"),
    )

}
