package com.newslist.news

import com.newslist.base.DispatcherProvider
import kotlinx.coroutines.withContext

class RemoteNewsRepository(private val api: NewsAPI, private val dispatcher: DispatcherProvider) :
    NewsRepository {
    override suspend fun getNews(): Result<List<News>> {
        return withContext(dispatcher.io()) {
            Result.success(listOf(News("Teste1", "Teste2")))
        }
    }
}