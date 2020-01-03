package nerd.tuxmobil.fahrplan.congress.navigation

object RoomForC3NavConverter {

    private const val EMPTY_STRING = ""

    private val ROOM_TO_C3NAV_MAPPING = mapOf(
            "AW1.120" to "aw1120",
            "AW1.121" to "aw1121",
            "AW1.125" to "aw1125",
            "AW1.126" to "aw1126",
            "H.1301 (CORNIL)" to "h1301_cornil",
            "H.1302 (DEPAGE)" to "h1302_depage",
            "H.1308 (ROLIN)" to "h1308_rolin",
            "H.1309 (VAN RIJN)" to "h1309_van_rijn",
            "H.2213" to "h2213",
            "H.2214" to "h2214",
            "H.2215 (FERRER)" to "h2215_ferr",
            "H.3242" to "h3242",
            "H.3244" to "h3244",
            "J.1.106" to "j1106",
            "JANSON" to "janson",
            "K.1.105 (LA FONTAINE)" to "k1105_la_fontaine",
            "K.3.201" to "k3201",
            "K.3.401" to "k3401",
            "K.4.201" to "k4201",
            "K.4.401" to "k4401",
            "K.4.601" to "k4601",
            "UA2.114 (BAUDOUX)" to "ua2114_baudoux",
            "UA2.220 (GUILLISSEN)" to "ua2220_guillissen",
            "UB2.147" to "ub2147",
            "UB2.252A (LAMEERE)" to "ub2252a_lameere",
            "UB4.132" to "ub4132",
            "UB4.136" to "ub4136",
            "UB4.228" to "ub4228",
            "UB5.132" to "ub5132",
            "UB5.230" to "ub5230",
            "UD2.119" to "ud2119",
            "UD2.120 (CHAVANNE)" to "ud2120_chavanne",
            "UD2.208 (DECROLY)" to "ud2208_decroly",
            "UD2.218A" to "ud2218a",
            "UD2.CORRIDOR" to "ud2corridor"
    )

    @JvmStatic
    fun convert(room: String?) = when {
        room != null && EMPTY_STRING != room -> {
            val c3navName = ROOM_TO_C3NAV_MAPPING[room.toUpperCase()]
            c3navName ?: EMPTY_STRING
        }
        else -> EMPTY_STRING
    }

}
