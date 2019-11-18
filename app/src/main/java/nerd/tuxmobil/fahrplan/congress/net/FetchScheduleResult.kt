package nerd.tuxmobil.fahrplan.congress.net

data class FetchScheduleResult(

        val httpStatus: HttpStatus,
        val scheduleXml: String = "",
        val eTag: String = "",
        val hostName: String,
        val exceptionMessage: String = ""

) {

    val isSuccessful
        get() = HttpStatus.HTTP_OK == httpStatus

    companion object {

        @JvmStatic
        fun createSuccess(hostName: String) =
                FetchScheduleResult(httpStatus = HttpStatus.HTTP_OK, hostName = hostName)

        @JvmStatic
        fun createError(httpStatus: HttpStatus, hostName: String) =
                FetchScheduleResult(httpStatus = httpStatus, hostName = hostName)

        @JvmStatic
        fun createException(httpStatus: HttpStatus, hostName: String, exceptionMessage: String?) =
                FetchScheduleResult(httpStatus = httpStatus, hostName = hostName, exceptionMessage = exceptionMessage ?: "")

    }

}
