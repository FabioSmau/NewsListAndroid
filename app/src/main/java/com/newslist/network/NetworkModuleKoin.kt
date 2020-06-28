package com.newslist.network

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

const val baseUrl = "https://newsapi.org"

val networkingModule = module {

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return RetrofitSettings(baseUrl, client).provide()
    }

    fun provideHttpClient(): OkHttpClient {
        return OkHttpSettings().provide()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }

}