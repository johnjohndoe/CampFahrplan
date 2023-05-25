package nerd.tuxmobil.fahrplan.congress.sponsors.factories

import nerd.tuxmobil.fahrplan.congress.R
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.BRONZE
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.DIAMOND
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.GOLD
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.ORGANIZERS
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.ORGANIZING_PARTNERS
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.PARTNERS
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.PLATINUM
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.SILVER
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Category.SUPPORTERS
import nerd.tuxmobil.fahrplan.congress.sponsors.models.Sponsor

object SponsorsFactory {

    fun createSponsors() = listOf(
        Sponsor(ORGANIZERS, R.drawable.organizer_101_osgeo, "OSGEO", "https://www.osgeo.org"),
        Sponsor(ORGANIZERS, R.drawable.organizer_102_flossk, "FLOSSK", "https://flossk.org"),

        Sponsor(DIAMOND, R.drawable.sponsor_diamond_101_re_earth, "Re:Earth", "https://2023.foss4g.org/sponsors/sponsor_reearth"),

        Sponsor(PLATINUM, R.drawable.sponsor_platinum_101_procreditbank, "ProCredit", "https://2023.foss4g.org/sponsors/sponsor_procredit"),
        Sponsor(PLATINUM, R.drawable.sponsor_platinum_102_itp, "ITP Prizren", "https://2023.foss4g.org/sponsors/sponsor_itp"),

        Sponsor(GOLD, R.drawable.sponsor_gold_101_kontur, "Kontur", "https://2023.foss4g.org/sponsors/sponsor_kontur"),

        Sponsor(SILVER, R.drawable.sponsor_silver_101_sparkgeo, "Sparkgeo", "https://2023.foss4g.org/sponsors/sponsor_sparkgeo"),
        Sponsor(SILVER, R.drawable.sponsor_silver_102_meta, "Meta", "https://2023.foss4g.org/sponsors/sponsor_meta"),
        Sponsor(SILVER, R.drawable.sponsor_silver_103_geosolutions, "GeoSolutions", "https://2023.foss4g.org/sponsors/sponsor_geosolutions"),
        Sponsor(SILVER, R.drawable.sponsor_silver_104_opengeogroep, "OpenGeoGroep", "https://2023.foss4g.org/sponsors/sponsor_opengeogroep"),
        Sponsor(SILVER, R.drawable.sponsor_silver_105_ipko, "IPKO", "https://2023.foss4g.org/sponsors/sponsor_ipko"),
        Sponsor(SILVER, R.drawable.sponsor_silver_106_geocat, "GEOCAT", "https://2023.foss4g.org/sponsors/sponsor_geocat"),
        Sponsor(SILVER, R.drawable.sponsor_silver_107_plateau, "OSGeo.JP", "https://2023.foss4g.org/sponsors/sponsor_osgeojp"),
        Sponsor(SILVER, R.drawable.sponsor_silver_108_giskos, "GISKOS", "https://2023.foss4g.org/sponsors/sponsor_giskos"),
        Sponsor(SILVER, R.drawable.sponsor_silver_109_qfield, "QField", "https://2023.foss4g.org/sponsors/sponsor_qfield"),

        Sponsor(BRONZE, R.drawable.sponsor_bronze_101_esa, "ESA", "https://2023.foss4g.org/sponsors/sponsor_esa"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_102_merginmaps, "Mergin Maps", "https://2023.foss4g.org/sponsors/sponsor_merginmaps"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_103_cloud68, "Cloud68", "https://2023.foss4g.org/sponsors/sponsor_cloud68co"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_104_rapidlasso, "Rapidlasso", "https://2023.foss4g.org/sponsors/sponsor_lastools"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_105_camptocamp, "Camptocamp", "https://2023.foss4g.org/sponsors/sponsor_camptocamp"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_106_eye, "EYE - Enhancing Youth Employmen", "https://helvetas-ks.org/eye"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_107_mundialis, "mundialis", "https://2023.foss4g.org/sponsors/sponsor_mundialis"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_108_terrestris, "terrestris", "https://2023.foss4g.org/sponsors/sponsor_terrestris"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_109_geoe3, "GEOE3", "https://2023.foss4g.org/sponsors/sponsor_geoe3_Locationinnovationhub"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_110_gis3w, "Gis3W", "https://2023.foss4g.org/sponsors/sponsor_gis3w"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_111_sinergise, "Sinergise", "https://2023.foss4g.org/sponsors/sponsor_sinergise"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_112_quarticle, "Quarticle", "https://2023.foss4g.org/sponsors/sponsor_quarticle"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_113_latlon, "lat/lon GmbH", "https://2023.foss4g.org/sponsors/sponsor_latlon"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_114_maptiler, "MapTiler", "https://2023.foss4g.org/sponsors/sponsor_maptiler"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_115_kan, "Kan Territory & IT", "https://2023.foss4g.org/sponsors/sponsor_kan"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_116_eox, "EOX", "https://2023.foss4g.org/sponsors/sponsor_eox"),
        Sponsor(BRONZE, R.drawable.sponsor_bronze_117_geobeyond, "Gobeyond", "https://2023.foss4g.org/sponsors/sponsor_geobeyond"),

        Sponsor(SUPPORTERS, R.drawable.sponsor_supporter_101_locatepress, "Locate Press", "https://2023.foss4g.org/sponsors/supporter_locatepress"),
        Sponsor(SUPPORTERS, R.drawable.sponsor_supporter_102_qgis_spain, "QGIS Spain Association", "https://2023.foss4g.org/sponsors/supporter_qgis_spain"),
        Sponsor(SUPPORTERS, R.drawable.sponsor_supporter_103_envirosolutions, "EnviroSolutions", "https://2023.foss4g.org/sponsors/supporter_envirosolutions"),
        Sponsor(SUPPORTERS, R.drawable.sponsor_supporter_104_oslandia, "Oslandia", "https://2023.foss4g.org/sponsors/supporter_oslandia"),
        Sponsor(SUPPORTERS, R.drawable.sponsor_supporter_105_geo_land, "Geo&Land", "https://2023.foss4g.org/sponsors/supporter_geo_land"),
        Sponsor(SUPPORTERS, R.drawable.sponsor_supporter_106_irn, "iRN", "https://2023.foss4g.org/sponsors/supporter_irn"),
        Sponsor(SUPPORTERS, R.drawable.sponsor_supporter_107_geodatalab, "GeoDataLab", "https://2023.foss4g.org/sponsors/supporter_geodatalab"),
        Sponsor(SUPPORTERS, R.drawable.sponsor_supporter_108_sourcepole, "Sourcepole", "http://2023.foss4g.org/sponsors/supporter_sourcepole"),

        Sponsor(PARTNERS, R.drawable.sponsor_partner_101_eu, "European Commission", "https://2023.foss4g.org/sponsors/partner_european_commission"),
        Sponsor(PARTNERS, R.drawable.sponsor_partner_102_kdwv, "The German-Kosovar Business Association", "https://2023.foss4g.org/sponsors/partner_kdwv"),
        Sponsor(PARTNERS, R.drawable.sponsor_partner_103_giz, "GIZ", "https://www.giz.de/en/worldwide/298.html"),

        Sponsor(ORGANIZING_PARTNERS, R.drawable.sponsor_organizing_partner_101_opsin, "OpS-IN", "https://2023.foss4g.org/sponsors/partner_ops_in"),
        Sponsor(ORGANIZING_PARTNERS, R.drawable.sponsor_organizing_partner_102_spacesyntaks, "SpaceSyntaks", "https://2023.foss4g.org/sponsors/partner_spacesyntaks"),
    )

}
