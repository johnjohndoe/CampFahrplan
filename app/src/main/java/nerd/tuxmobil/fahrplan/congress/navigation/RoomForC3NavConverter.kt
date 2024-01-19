package nerd.tuxmobil.fahrplan.congress.navigation

class RoomForC3NavConverter {

    companion object {

        private const val EMPTY_STRING = ""

        // Keys must be uppercase!
        private val ROOM_TO_C3NAV_MAPPING = mapOf(
            // FOSDEM 2024

            "AW1.120" to "aw1120",
            "AW1.121" to "aw1121",
            "AW1.125" to "aw1125",
            "AW1.126" to "aw1126",
            "H.1301 (CORNIL)" to "h1301",
            "H.1302 (DEPAGE)" to "h1302",
            "H.1308 (ROLIN)" to "h1308",
            "H.1309 (VAN RIJN)" to "h1309",
            "H.2111" to "h2111",
            "H.2213" to "h2213",
            "H.2214" to "h2214",
            "H.2215 (FERRER)" to "h2215",
            "H.3242" to "h3242",
            "H.3244" to "h3244",
            "J.1.106" to "j1106",
            "JANSON" to "janson",
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
            "UB2.252A (LAMEERE)" to "ub2252a",
            "UB4.132" to "ub4132",
            "UB4.136" to "ub4136",
            "UB4.228" to "ub4228",
            "UB5.132" to "ub5132",
            "UB5.230" to "ub5230",
            "UD2.119" to "ud2110",
            "UD2.120 (CHAVANNE)" to "ud2120",
            "UD2.208 (DECROLY)" to "ud2208",
            "UD2.218A" to "ud2218a",

            // Congress
            "ADA" to "hall-a",
            "BORG" to "hall-b",
            "CLARKE" to "hall-c",
            "DIJKSTRA" to "hall-d",
            "ELIZA" to "hall-e",

            // From everything.schedule.xml
            "ART-AND-PLAY INSTALLATION" to "artandplay",
            "ART-AND-PLAY STAGE" to "ap-stage",
            "ASSEMBLY:ANARCHIST VILLAGE" to "anarchist-assembly",
            "ASSEMBLY:BACKSPACE" to "backspace",
            "ASSEMBLY:BLIND NAVIGATION WORKSHOP" to "blindnavigation",
            "ASSEMBLY:C-BASE / CCC-B / XHAIN" to "c-base-workshop",
            "ASSEMBLY:C3BLIND" to "c3blind",
            "ASSEMBLY:CHAOS WEST" to "cw-stage",
            "ASSEMBLY:CHAOSZONE" to "chaoszone",
            "ASSEMBLY:CRYPTO CURRENCY ASSEMBLY / EMBASSY" to "crypto-currency",
            "ASSEMBLY:CURRY CLUB AUGSBURG" to "openlab-augsburg",
            "ASSEMBLY:FOODHACKINGBASE" to "fhb",
            "ASSEMBLY:FOSSASIA" to "fossasia",
            "ASSEMBLY:FREE SOFTWARE FOUNDATION EUROPE" to "fsfe",
            "ASSEMBLY:HAECKSEN" to "haecksen",
            "ASSEMBLY:HARDWARE HACKING AREA" to "hardwarehackingarea",
            "ASSEMBLY:HSBE" to "hsbe",
            "ASSEMBLY:ICMP" to "icmp",
            "ASSEMBLY:MILLIWAYS" to "milliways",
            "ASSEMBLY:MOIN - MEHRERE ORTE IM NORDEN" to "moin",
            "ASSEMBLY:NIBBLE AREA" to "nibble",
            "ASSEMBLY:NIXOS" to "nixos",
            "ASSEMBLY:OPEN INFRASTRUCTURE ORBIT" to "oio",
            "ASSEMBLY:PSEUDOROOM" to "pseudoroom",
            "ASSEMBLY:SPACE IN FRONT OF M1" to "mh-hall-m1-m2-foyer",
            "ASSEMBLY:VINTAGE COMPUTING" to "vintage",
            "ASSEMBLY:WIKIPAKAWG" to "wikipaka-wg",
            "BÜHNE" to null,
            "C-BASE" to "c-base",
            "CCL HALL 3" to "dlf-sendezentrum",
            "CCL SAAL 3" to "ccl-hall-3",
            "CCL SAAL 3" to "dlf-sendezentrum",
            "CCL TERRACE" to "ccl-terrace",
            "CDC - STAGE" to "cdc-stage",
            "CDC - WORKSHOP AREA" to "cdc",
            "CHAOS-WEST BÜHNE" to "cw-stage",
            "CHAOSZONE BÜHNE" to "chaoszone-stage",
            "CHAOSZONE WORKSHOP" to "cz-workshop",
            "CHAOSZONE" to "chaoszone",
            "COMPEILER" to "compeiler",
            "DEZENTRALE*" to "dezentrale",
            "DISCODRAMA" to "discodrama",
            "DLF- UND PODCAST-BÜHNE" to "dlf-sendezentrum",
            "FOSSASIA WORKSHOPS - CDC" to "fossasia",
            "HACKERS BEAUTY PALACE" to "hbp",
            "HEADNUT" to "komona-headnut",
            "KIDSPACE" to "kidspace",
            "LECTURE ROOM 11" to "self-organized-sessions-11",
            "LECTURE ROOM M1" to "self-organized-sessions-m1",
            "LECTURE ROOM M2" to "self-organized-sessions-m2",
            "LECTURE ROOM M3" to "self-organized-sessions-m3",
            "MONIPILAMI" to "monipilami",
            "NOKINGDOME" to "komona-nokingdome",
            "OIO LECTURE ARENA" to "oio-arena",
            "OIO LÖTAREA" to "oio-soldering",
            "OIO SOLDER AREA" to "oio-workshop",
            "OIO STAGE" to "oio-stage",
            "OIO THEMENTISCH 1" to "oio-table1",
            "OIO THEMENTISCH 2" to "oio-table2",
            "OIO THEMENTISCH 3" to "oio-table3",
            "OIO THEMENTISCH 4" to "oio-table4",
            "OIO THEMENTISCH 6" to "oio-table6",
            "OIO VORTRAGS-ARENA" to "oio-arena",
            "OIO WORKSHOP" to "oio-workshop-dome",
            "OIO WORKSHOP-DOMO" to "oio-workshop-dome",
            "SEMINAR ROOM 13" to "self-organized-sessions-13",
            "SEMINAR ROOM 14-15" to "self-organized-sessions-14-15",
            "SENDETISCH" to "sendetisch",
            "SHUTTER ISLAND" to "shutter-island",
            "SOUPWORX" to "komona-soupworx",
            "STUDIO DATSCHE" to "studio-datscha",
            "UPTIME BAR" to "uptime-bar",
            "VINTAGE COMPUTING CLUSTER" to "vintage",
            "WIKIPAKA WG: BIBLIOTHEK" to "wikipaka-library",
            "WIKIPAKA WG: ESSZIMMER" to "wikipaka-dining"
        )

    }

    fun convert(roomName: String?) = when {
        roomName != null && EMPTY_STRING != roomName -> {
            val c3navName = ROOM_TO_C3NAV_MAPPING[roomName.uppercase()]
            c3navName ?: EMPTY_STRING
        }

        else -> EMPTY_STRING
    }

}
