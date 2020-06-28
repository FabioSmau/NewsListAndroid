package com.newslist.news.recycler

import com.newslist.news.repository.Article

object NewsContentConverter {

    fun convert(article: List<Article>): List<NewsContent> {
        if (article.isEmpty()) {
            return listOf(EmptyNewsHolder())
        }

        val newsContent = mutableListOf<NewsContent>()

        val firstArticle = article.first()
        newsContent.add(HeaderNewsHolder(firstArticle))

        article.forEach {
            newsContent.add(LineNewsHolder(it))
        }
        return newsContent
    }



}