package info.metadude.android.eventfahrplan.sessionize

import info.metadude.kotlin.library.sessionize.SessionizeService
import info.metadude.kotlin.library.sessionize.gridtable.models.ConferenceDay
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrDefault

class SessionizeNetworkRepository(

        private val service: SessionizeService,
        private val apiKey: String) {

    suspend fun loadConferenceDays(onDataLoad: (sessionizeResult: SessionizeResult) -> Unit) {
        val call = service.getConferenceDays(apiKey)
        val result: Result<List<ConferenceDay>> = call.awaitResult()
        val sessionizeResult = when (result) {
            is Result.Ok -> {
                val default = emptyList<ConferenceDay>()
                SessionizeResult.Values(result.getOrDefault(default))
            }
            is Result.Error -> SessionizeResult.Error(result.response.code(), result.exception.message())
            is Result.Exception -> SessionizeResult.Exception(result.exception)
        }
        onDataLoad(sessionizeResult)
    }

}
