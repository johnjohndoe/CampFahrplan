package info.metadude.android.eventfahrplan.network.repositories

import info.metadude.kotlin.library.droidconberlin.models.Session

sealed class DroidconBerlinResult {

    data class Values(val sessions: List<Session>) : DroidconBerlinResult()
    data class Error(val text: String) : DroidconBerlinResult()
    data class Exception(val throwable: Throwable) : DroidconBerlinResult()

}
