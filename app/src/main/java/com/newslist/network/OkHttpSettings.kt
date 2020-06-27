package com.newslist.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val TIME_OUT_SECONDS = 60L

class OkHttpSettings : NetworkSettingsProvider<OkHttpClient> {

    override fun provide(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(LoggingInterceptorSettings().provide())
            .build()
    }

}