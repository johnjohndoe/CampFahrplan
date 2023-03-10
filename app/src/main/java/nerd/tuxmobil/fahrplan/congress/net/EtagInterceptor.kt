package nerd.tuxmobil.fahrplan.congress.net

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

internal class EtagInterceptor(

    private val eTag: String

) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val originalRequest = chain.request()
        val requestWithEtag = originalRequest.newBuilder()
            .header("If-None-Match", eTag)
            .build()
        return chain.proceed(requestWithEtag)
    }

}
