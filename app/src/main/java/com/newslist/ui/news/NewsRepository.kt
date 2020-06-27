package com.newslist.ui.news

interface NewsRepository {
    fun getNews()

    companion object {
        fun create() = RemoteNewsRepository()
    }
}