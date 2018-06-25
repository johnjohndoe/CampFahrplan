package info.metadude.android.eventfahrplan.network.repositories

import info.metadude.kotlin.library.droidconberlin.ApiService
import info.metadude.kotlin.library.droidconberlin.models.Session
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrDefault
import java.net.UnknownHostException

class DroidconBerlinNetworkRepository(

        private val service: ApiService

) {

    suspend fun loadSessions(onSessionsLoad: (sessionizeResult: DroidconBerlinResult) -> Unit) {
        val call = service.getSessions()
        val result: Result<List<Session>>
        var droidconBerlinResult: DroidconBerlinResult
        try {
            result = call.awaitResult()
            droidconBerlinResult = when (result) {
                is Result.Ok -> {
                    DroidconBerlinResult.Values(result.getOrDefault(emptyList()))
                }
                is Result.Error -> DroidconBerlinResult.Error("")
                is Result.Exception -> DroidconBerlinResult.Exception(result.exception)
            }
        } catch (e: UnknownHostException) {
            // Being offline
            droidconBerlinResult = DroidconBerlinResult.Exception(e)
        }
        onSessionsLoad(droidconBerlinResult)
    }

}
