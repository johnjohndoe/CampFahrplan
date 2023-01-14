package nerd.tuxmobil.fahrplan.congress.sponsors.factories

import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.BRONZE
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.GOLD
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.PLATINUM
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.SILVER
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor

object SponsorsFactory {

    fun createSponsors() = listOf(
        Sponsor(PLATINUM, R.drawable.sponsor_001_camptocamp, "camptocamp", "https://fossgis-konferenz.de/2023/sponsor/001_camp2camp.php"),
        Sponsor(PLATINUM, R.drawable.sponsor_002_wheregroup, "WhereGroup GmbH", "https://fossgis-konferenz.de/2023/sponsor/002_wheregroup.php"),
        Sponsor(PLATINUM, R.drawable.sponsor_003_hu, "Humboldt-Universität zu Berlin", "https://fossgis-konferenz.de/2023/sponsor/003_hu-berlin.php"),
        Sponsor(PLATINUM, R.drawable.sponsor_004_tsb, "Technologiestiftung Berlin", "https://fossgis-konferenz.de/2023/sponsor/004_TSB.php"),

        Sponsor(GOLD, R.drawable.sponsor_101_bkg, "Bundesamt für Kartographie und Geodäsie", "https://fossgis-konferenz.de/2023/sponsor/101_bkg.php"),
        Sponsor(GOLD, R.drawable.sponsor_102_opengis, "OPENGIS.ch GmbH", "https://fossgis-konferenz.de/2023/sponsor/102_OPENGis_CH.php"),

        Sponsor(SILVER, R.drawable.sponsor_201_terrestris, "terrestris GmbH & Co. KG", "https://fossgis-konferenz.de/2023/sponsor/201_terrestris.php"),
        Sponsor(SILVER, R.drawable.sponsor_202_schneider_digital, "Schneider Digital", "https://fossgis-konferenz.de/2023/sponsor/202_schneider-digital.php"),

        Sponsor(BRONZE, R.drawable.sponsor_401_komoot, "komoot GmbH", "https://fossgis-konferenz.de/2023/sponsor/401_komoot.php"),
        Sponsor(BRONZE, R.drawable.sponsor_402_mundialis, "mundialis GmbH & Co. KG", "https://fossgis-konferenz.de/2023/sponsor/402_mundialis.php"),
        Sponsor(BRONZE, R.drawable.sponsor_403_sourcepole, "Sourcepole AG", "https://fossgis-konferenz.de/2023/sponsor/403_sourcepole.php"),
        Sponsor(BRONZE, R.drawable.sponsor_404_52north, "52°North Spatial Information Research GmbH", "https://fossgis-konferenz.de/2023/sponsor/404_52north.php"),
        Sponsor(BRONZE, R.drawable.sponsor_405_opencage, "Open Cage GmbH", "https://fossgis-konferenz.de/2023/sponsor/405_opencage.php"),
        Sponsor(BRONZE, R.drawable.sponsor_406_wagner_it, "Wagner-IT", "https://fossgis-konferenz.de/2023/sponsor/406_wagner-it.php"),
        Sponsor(BRONZE, R.drawable.sponsor_407_gbd, "Geoinformatikbüro Dassau GmbH", "https://fossgis-konferenz.de/2023/sponsor/407_gbd.php"),
        Sponsor(BRONZE, R.drawable.sponsor_408_latlon, "lat/lon gesellschaft für raumbezogene Informationssysteme mbH", "https://fossgis-konferenz.de/2023/sponsor/408_latlon.php"),
        Sponsor(BRONZE, R.drawable.sponsor_410_geoinfo, "GEOINFO Applications AG", "https://fossgis-konferenz.de/2023/sponsor/410_GEOINFO.php"),
        Sponsor(BRONZE, R.drawable.sponsor_409_gkg, "GKG Kassel", "https://fossgis-konferenz.de/2023/sponsor/409_gkg.php"),
        Sponsor(BRONZE, R.drawable.sponsor_411_geosys, "geoSYS", "https://fossgis-konferenz.de/2023/sponsor/411_geoSYS.php"),
        Sponsor(BRONZE, R.drawable.sponsor_412_geofabrik, "Geofabrik GmbH", "https://fossgis-konferenz.de/2023/sponsor/412_geofabrik.php"),
        Sponsor(BRONZE, R.drawable.sponsor_413_dbg, "d.b.g. Datenbankgesellschaft mbH", "https://fossgis-konferenz.de/2023/sponsor/413_DBG.php"),
        Sponsor(BRONZE, R.drawable.sponsor_414_landplanos, "LandPlan OS GmbH", "https://fossgis-konferenz.de/2023/sponsor/414_LandPlanOS.php"),
        Sponsor(BRONZE, R.drawable.sponsor_415_tomtom, "TomTom", "https://fossgis-konferenz.de/2023/sponsor/415_TomTom.php"),
    )

}
