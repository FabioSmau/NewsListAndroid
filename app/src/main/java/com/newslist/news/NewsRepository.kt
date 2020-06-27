package com.newslist.news

interface NewsRepository {
    suspend fun getNews(): Result<List<News>>
}