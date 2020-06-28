package com.newslist.news.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsRecyclerAdapter: RecyclerView.Adapter<NewsViewHolder>() {

    private val newsContent = mutableListOf<NewsContent>()

    fun update(content: List<NewsContent>) {
        this.newsContent.clear()
        this.newsContent.addAll(content)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return newsContent[position].viewType()
    }

    override fun getItemCount(): Int {
        return newsContent.count()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        newsContent[position].setupHolder(holder)
    }
}

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)