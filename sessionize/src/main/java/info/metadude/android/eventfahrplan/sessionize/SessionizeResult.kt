package info.metadude.android.eventfahrplan.sessionize

import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay

sealed class SessionizeResult {

    data class Values(val conferenceDays: List<ConferenceDay>) : SessionizeResult()
    data class Error(val httpStatusCode: Int, val exceptionMessage: String) : SessionizeResult()
    data class Exception(val throwable: Throwable) : SessionizeResult()

}
