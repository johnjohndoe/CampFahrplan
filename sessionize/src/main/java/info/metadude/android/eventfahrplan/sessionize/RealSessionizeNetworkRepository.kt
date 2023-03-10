package info.metadude.android.eventfahrplan.sessionize

import info.metadude.kotlin.library.sessionize.ApiModule
import okhttp3.OkHttpClient
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrDefault
import java.io.IOException

class RealSessionizeNetworkRepository(

    private val apiKey: String,
    private val sessionizeApi: ApiModule = ApiModule

) : SessionizeNetworkRepository {

    override suspend fun loadConferenceDays(okHttpClient: OkHttpClient, baseUrl: String): SessionizeResult {
        val service = sessionizeApi.provideSessionizeService(baseUrl, okHttpClient)
        val call = service.getConferenceDays(apiKey)
        // TODO Replace with retrofit2.awaitResponse, see d616cee9a6bd0f59e474a9a4e7c92efc45792b7a.
        return when (val result = safeApiCall(
                call = { call.awaitResult() },
                errorMessage = "Error loading schedule."
        )) {
            is Result.Ok -> SessionizeResult.Values(result.getOrDefault(emptyList()), result.response.header("ETag").orEmpty())
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
