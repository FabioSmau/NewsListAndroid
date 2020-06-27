package com.newslist.network

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptorSettings : NetworkSettingsProvider<Interceptor> {

    override fun provide(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}