package info.metadude.android.eventfahrplan.network.repositories

import info.metadude.kotlin.library.droidconberlin.ApiService
import info.metadude.kotlin.library.droidconberlin.models.Session
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrDefault

class DroidconBerlinNetworkRepository(

        private val service: ApiService

) {

    suspend fun loadSessions(onSessionsLoad: (sessionizeResult: DroidconBerlinResult) -> Unit) {
        val call = service.getSessions()
        val result: Result<List<Session>> = call.awaitResult()
        val droidconBerlinResult = when (result) {
            is Result.Ok -> {
                DroidconBerlinResult.Values(result.getOrDefault(emptyList()))
            }
            is Result.Error -> DroidconBerlinResult.Error("")
            is Result.Exception -> DroidconBerlinResult.Exception(result.exception)
        }
        onSessionsLoad(droidconBerlinResult)
    }

}
