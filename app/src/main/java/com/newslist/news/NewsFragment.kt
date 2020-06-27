package com.newslist.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.newslist.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private val newsViewModel by viewModel<NewsViewModel>()

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycle.addObserver(newsViewModel)
        // TODO: Use the ViewModel
    }

}