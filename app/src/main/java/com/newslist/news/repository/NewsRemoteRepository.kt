package com.newslist.news.repository

import com.newslist.base.DispatcherProvider
import kotlinx.coroutines.withContext
import java.io.IOException

class NewsRemoteRepository(private val api: NewsAPI, private val dispatcher: DispatcherProvider) :
    NewsRepository {

    override suspend fun getNews(): Result<NewsResponse> =
        withContext(dispatcher.io()) {
            val response = api.getNews().await()
            try {
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure<NewsResponse>(Exception("Error occurred during fetching news!"))
                }
            } catch (e: Exception) {
                Result.failure<NewsResponse>(IOException("Unable to fetch news!"))
            }
        }

}