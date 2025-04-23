package nerd.tuxmobil.fahrplan.congress.dataconverters

import info.metadude.kotlin.library.sessionize.repositories.models.GetConferenceDaysState
import nerd.tuxmobil.fahrplan.congress.net.HttpStatus

val GetConferenceDaysState.Error.httpStatus
    get() = when (httpStatusCode) {
        304 -> HttpStatus.HTTP_NOT_MODIFIED
        401 -> HttpStatus.HTTP_WRONG_HTTP_CREDENTIALS
        404 -> HttpStatus.HTTP_NOT_FOUND
        else -> HttpStatus.HTTP_COULD_NOT_CONNECT
    }
