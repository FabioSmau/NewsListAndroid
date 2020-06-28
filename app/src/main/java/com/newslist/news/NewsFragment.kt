package com.newslist.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.newslist.R
import com.newslist.news.recycler.NewsContentConverter
import com.newslist.news.recycler.NewsRecyclerAdapter
import com.newslist.news.repository.Article
import kotlinx.android.synthetic.main.news_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(R.layout.news_list_fragment) {

    private val newsViewModel by viewModel<NewsViewModel>()
    private val adapter = NewsRecyclerAdapter()

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(newsViewModel)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        recycler_news.adapter = adapter
        recycler_news.layoutManager = LinearLayoutManager(context)
    }

    private fun setupViewModel() {
        newsViewModel.observeViewAction(this, Observer { viewAction ->
            when (viewAction) {
                is NewsViewModel.ViewAction.Loading -> showLoading()
                is NewsViewModel.ViewAction.NewsLoaded -> {
                    hideLoading()
                    updateRecyclerView(viewAction.articles)
                }
            }
        })
    }

    private fun showLoading() {
        progress_loading.visibility = View.VISIBLE
        recycler_news.visibility = View.GONE
    }

    private fun hideLoading() {
        progress_loading.visibility = View.GONE
        recycler_news.visibility = View.VISIBLE
    }

    private fun updateRecyclerView(article: List<Article>) {
        adapter.update(NewsContentConverter.convert(article))
    }
}


