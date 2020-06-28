package com.newslist.news.repository

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/v2/everything")
    fun getNews(@Query("q") sources: String?= "bitcoin", @Query("page") page: Int = 1, @Query("pageSize") pageSize: Int = 50): Deferred<Response<NewsResponse>>
}