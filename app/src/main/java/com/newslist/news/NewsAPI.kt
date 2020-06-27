package com.newslist.news

import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {
    @GET("users/{user}/repos")
    fun getNews(): Call<List<News>>
}