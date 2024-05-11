package info.metadude.android.eventfahrplan.sessionize

import okhttp3.OkHttpClient

interface SessionizeNetworkRepository {

    suspend fun loadConferenceDays(okHttpClient: OkHttpClient, baseUrl: String): SessionizeResult

}
