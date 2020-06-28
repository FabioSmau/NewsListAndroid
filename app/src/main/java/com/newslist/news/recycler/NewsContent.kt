package com.newslist.news.recycler

interface NewsContent {
    fun viewType(): Int
    fun setupHolder(holder: NewsViewHolder?)
}
