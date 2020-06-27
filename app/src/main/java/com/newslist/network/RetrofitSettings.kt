package com.newslist.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSettings(
    private val url: String,
    private val okHttp: OkHttpClient
) : NetworkSettingsProvider<Retrofit> {

    override fun provide(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
    }
}