package nerd.tuxmobil.fahrplan.congress.navigation

object RoomForC3NavConverter {

    private const val EMPTY_STRING = ""

    private val ROOM_TO_C3NAV_MAPPING = mapOf(
            "AW1.120" to "aw1-120",
            "AW1.121" to "aw1121",
            "AW1.125" to "aw1125",
            "AW1.126" to "aw1-126",
            "H.1301 (CORNIL)" to "h1301",
            "H.1302 (DEPAGE)" to "h1302",
            "H.1308 (ROLIN)" to "h1308",
            "H.1309 (VAN RIJN)" to "h1309",
            "H.2213" to "h2213",
            "H.2214" to "h2214",
            "H.2215 (FERRER)" to "h2215",
            "JANSON" to "j-main",
            "K.1.105 (LA FONTAINE)" to "k1105",
            "K.3.201" to "k3201",
            "K.3.401" to "k3401",
            "K.4.201" to "k4201",
            "K.4.401" to "k4401",
            "K.4.601" to "k4601",
            "UA2.114 (BAUDOUX)" to "ua2114",
            "UA2.118 (HENRIOT)" to "ua2118",
            "UA2.220 (GUILLISSEN)" to "ua2220",
            "UB2.147" to "ub2147",
            "UB2.252A (LAMEERE)" to "ub2-252a",
            "UD2.119" to "ud2119",
            "UD2.120 (CHAVANNE)" to "ud2120",
            "UD2.208 (DECROLY)" to "ud2208",
            "UD2.218A" to "ud2218a",
            "UD2.CORRIDOR" to "545fe4c1e56d7"
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
