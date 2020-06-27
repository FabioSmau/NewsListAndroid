package com.newslist.news

interface NewsRepository {
    fun getNews()

    companion object {
        fun create() = RemoteNewsRepository()
    }
}