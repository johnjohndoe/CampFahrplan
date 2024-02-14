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
        Sponsor(PLATINUM,R.drawable.sponsor_001_wheregroup,"WhereGroup GmbH","https://fossgis-konferenz.de/2024/sponsor/001_wheregroup.php"),
        Sponsor(PLATINUM,R.drawable.sponsor_002_camptocamp,"camptocamp SA","https://fossgis-konferenz.de/2024/sponsor/002_camp2camp.php"),
        Sponsor(PLATINUM,R.drawable.sponsor_003_dataport,"Dataport","https://fossgis-konferenz.de/2024/sponsor/003_dataport.php"),

        Sponsor(GOLD,R.drawable.sponsor_101_bkg,"BKG","https://fossgis-konferenz.de/2024/sponsor/101_bkg.php"),
        Sponsor(GOLD,R.drawable.sponsor_102_lgv,"Landesbetrieb Geoinformation und Vermessung","https://fossgis-konferenz.de/2024/sponsor/102_LGV.php"),
        Sponsor(GOLD,R.drawable.sponsor_103_opengisch,"OPENGIS.ch","https://fossgis-konferenz.de/2024/sponsor/103_OPENGis_CH.php"),
        Sponsor(GOLD,R.drawable.sponsor_104_mobidrom,"NRW.Mobidrom GmbH","https://fossgis-konferenz.de/2024/sponsor/104_NRW-Mobidrom.php"),

        Sponsor(SILVER,R.drawable.sponsor_201_hbt,"HBT Hamburger Berater Team GmbH","https://fossgis-konferenz.de/2024/sponsor/201_HBT.php"),

        Sponsor(BRONZE,R.drawable.sponsor_401_opencage,"OpenCage GmbH","https://fossgis-konferenz.de/2024/sponsor/401_opencage.php"),
        Sponsor(BRONZE,R.drawable.sponsor_402_terrestris,"terrestris","https://fossgis-konferenz.de/2024/sponsor/402_terrestris.php"),
        Sponsor(BRONZE,R.drawable.sponsor_403_mundialis,"mundialis","https://fossgis-konferenz.de/2024/sponsor/403_mundialis.php"),
        Sponsor(BRONZE,R.drawable.sponsor_404_52north,"52North","https://fossgis-konferenz.de/2024/sponsor/404_52north.php"),
        Sponsor(BRONZE,R.drawable.sponsor_405_isb_conet,"CONET ISB GmbH","https://fossgis-konferenz.de/2024/sponsor/405_CONET-ISB.php"),
        Sponsor(BRONZE,R.drawable.sponsor_406_gbd,"Geoinformatikbüro Dassau","https://fossgis-konferenz.de/2024/sponsor/406_gbd.php"),
        Sponsor(BRONZE,R.drawable.sponsor_407_grit,"grit GmbH","https://fossgis-konferenz.de/2024/sponsor/407_grit.php"),
        Sponsor(BRONZE,R.drawable.sponsor_408_latlon,"LatLon","https://fossgis-konferenz.de/2024/sponsor/408_latlon.php"),
        Sponsor(BRONZE,R.drawable.sponsor_409_sourcepole,"Sourcepole","https://fossgis-konferenz.de/2024/sponsor/409_sourcepole.php"),
        Sponsor(BRONZE,R.drawable.sponsor_410_geosys,"Geosys","https://fossgis-konferenz.de/2024/sponsor/410_geoSYS.php"),
        Sponsor(BRONZE,R.drawable.sponsor_411_komoot,"kommot","https://fossgis-konferenz.de/2024/sponsor/411_komoot.php"),
        Sponsor(BRONZE,R.drawable.sponsor_412_norbit,"norbit","https://fossgis-konferenz.de/2024/sponsor/412_norBIT.php"),
        Sponsor(BRONZE,R.drawable.sponsor_413_indiscale,"IndiScale GmbH","https://fossgis-konferenz.de/2024/sponsor/413_indiscale.php"),
        Sponsor(BRONZE,R.drawable.sponsor_414_tomtom,"TomTom","https://fossgis-konferenz.de/2024/sponsor/414_TomTom.php"),
        Sponsor(BRONZE,R.drawable.sponsor_415_gkg_logo,"GKG Kassel Claas Leiner","https://fossgis-konferenz.de/2024/sponsor/415_gkg.php"),
        Sponsor(BRONZE,R.drawable.sponsor_416_geops,"geOps","https://fossgis-konferenz.de/2024/sponsor/416_geOps.php"),
        Sponsor(BRONZE,R.drawable.sponsor_417_geofabrik,"Geofabrik","https://fossgis-konferenz.de/2024/sponsor/417_geofabrik.php"),
        Sponsor(BRONZE,R.drawable.sponsor_418_ioki,"ioki","https://fossgis-konferenz.de/2024/sponsor/418_ioki.php"),
        Sponsor(BRONZE,R.drawable.sponsor_419_ginger_lehmann_partner,"LEHMANN + PARTNER GmbH","https://fossgis-konferenz.de/2024/sponsor/419_lehmann.php"),

        Sponsor(MEDIA_PARTNER,R.drawable.sponsor_501_osgeolive,"OSGeoLive","https://live.osgeo.org"),
        Sponsor(MEDIA_PARTNER,R.drawable.sponsor_502_c3voc,"C3VOC","https://c3voc.de"),
        Sponsor(MEDIA_PARTNER,R.drawable.sponsor_503_tib,"TIB","https://av.tib.eu/search?f=publisher%3Bhttp://av.tib.eu/resource/FOSSGIS_e.V."),
        Sponsor(MEDIA_PARTNER,R.drawable.sponsor_504_oreilly,"O’Reilly","https://www.oreilly.de"),
    )

}
