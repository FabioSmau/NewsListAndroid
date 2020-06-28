package com.newslist.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

private const val TIME_OUT_SECONDS = 60L
private const val apiKeyHeader = "X-Api-Key"
private const val apiKey = "a4aa063220574efa9e937bcabbe0cd4b"

class OkHttpSettings : NetworkSettingsProvider<OkHttpClient> {

    override fun provide(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptorSettings().provide())
            .addInterceptor(::addHeaders)
            .build()
    }

    private fun addHeaders(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader(apiKeyHeader, apiKey).build()
        return chain.proceed(request)
    }

}