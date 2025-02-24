package nerd.tuxmobil.fahrplan.congress.utils

import nerd.tuxmobil.fahrplan.congress.models.Session

interface SessionPropertiesFormatting {

    fun getFormattedLinks(links: String): String

    fun getFormattedUrl(url: String): String

    fun getFormattedSpeakers(session: Session): String

    fun getFormattedTrackNameAndLanguageText(session: Session): String

    fun getLanguageText(session: Session): String

    fun getRoomName(roomName: String, defaultEngelsystemRoomName: String, customEngelsystemRoomName: String): String
}
