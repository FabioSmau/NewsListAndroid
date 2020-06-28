package com.newslist.news.repository

data class NewsResponse (
    val totalResults: Int,
    val articles: List<Article>
)