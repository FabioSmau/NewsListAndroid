package com.newslist.news.recycler

import com.newslist.R
import com.newslist.extensions.loadImage
import com.newslist.news.repository.Article
import kotlinx.android.synthetic.main.news_header_recycler_item.view.*

class HeaderNewsHolder(private val article: Article) : NewsContent {

    override fun viewType(): Int {
        return R.layout.news_header_recycler_item
    }

    override fun setupHolder(holder: NewsViewHolder?) {
        val itemView = holder?.itemView?.rootView
        itemView?.image_view_news?.loadImage(article.urlToImage)
        itemView?.text_view_news_title?.text = article.title
    }

}