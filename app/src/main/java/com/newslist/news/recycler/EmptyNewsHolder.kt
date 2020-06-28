package com.newslist.news.recycler

import com.newslist.R

class EmptyNewsHolder : NewsContent {
    override fun viewType(): Int {
        return R.layout.news_empty_recycler_item
    }

    override fun setupHolder(holder: NewsViewHolder?) {

    }
}