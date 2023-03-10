package nerd.tuxmobil.fahrplan.congress.dataconverters

import nerd.tuxmobil.fahrplan.congress.net.HttpStatus
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLException

fun Throwable.toHttpStatus() = when (this) {
    is KeyManagementException -> HttpStatus.HTTP_SSL_SETUP_FAILURE
    is NoSuchAlgorithmException -> HttpStatus.HTTP_SSL_SETUP_FAILURE
    is SSLException -> HttpStatus.HTTP_LOGIN_FAIL_UNTRUSTED_CERTIFICATE
    is SocketTimeoutException -> HttpStatus.HTTP_CONNECT_TIMEOUT
    is UnknownHostException -> HttpStatus.HTTP_DNS_FAILURE
    is IOException -> HttpStatus.HTTP_COULD_NOT_CONNECT
    else -> HttpStatus.HTTP_COULD_NOT_CONNECT
}
