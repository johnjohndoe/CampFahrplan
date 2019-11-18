package info.metadude.android.eventfahrplan.sessionize

import info.metadude.kotlin.library.sessionize.SessionizeService
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrDefault
import java.io.IOException

class SessionizeNetworkRepository(

        private val service: SessionizeService,
        private val apiKey: String) {

    suspend fun loadConferenceDays(): SessionizeResult {
        val call = service.getConferenceDays(apiKey)
        return when (val result = safeApiCall(
                call = { call.awaitResult() },
                errorMessage = "Error loading schedule."
        )) {
            is Result.Ok -> SessionizeResult.Values(result.getOrDefault(emptyList()))
            is Result.Error -> SessionizeResult.Error(result.response.code(), result.exception.message())
            is Result.Exception -> SessionizeResult.Exception(result.exception)
        }
    }

    /**
     * Wrap a suspending API [call] in try/catch. In case an exception is thrown,
     * a [Result.Exception] is created based on the [errorMessage].
     */
    private suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            Result.Exception(IOException(errorMessage, e))
        }
    }

}
