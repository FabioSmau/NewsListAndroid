package com.newslist.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.newslist.R
import com.newslist.news.recycler.NewsContentFactory
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
        setupSwipeToRefresh()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        recycler_news.adapter = adapter
        recycler_news.layoutManager = LinearLayoutManager(context)
    }

    private fun setupSwipeToRefresh() {
        swipe_to_refresh.setOnRefreshListener { newsViewModel.loadNews() }
    }

    private fun setupViewModel() {
        newsViewModel.getViewState().observe(viewLifecycleOwner, Observer { viewAction ->
            when (viewAction) {
                is NewsViewModel.ViewAction.Loading -> showLoading()
                is NewsViewModel.ViewAction.Error -> setupEmptyState()
                is NewsViewModel.ViewAction.NewsLoaded -> setupLoadedState(viewAction.articles)
            }
        })
    }

    private fun showLoading() {
        swipe_to_refresh.isRefreshing = true
        recycler_news.visibility = View.GONE
    }

    private fun hideLoading() {
        swipe_to_refresh.isRefreshing = false
        recycler_news.visibility = View.VISIBLE
    }

    private fun setupEmptyState() {
        hideLoading()
        adapter.update(NewsContentFactory.empty())
    }

    private fun setupLoadedState(article: List<Article>) {
        hideLoading()
        adapter.update(NewsContentFactory.create(article))
    }
}


