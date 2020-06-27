package com.newslist.ui.main

interface NewsRepository {
    fun getNews()

    companion object {
        fun create() = RemoteNewsRepository()
    }
}