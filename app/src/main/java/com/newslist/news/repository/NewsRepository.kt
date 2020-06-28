package com.newslist.news.repository

interface NewsRepository {
    suspend fun getNews(): Result<NewsResponse>
}