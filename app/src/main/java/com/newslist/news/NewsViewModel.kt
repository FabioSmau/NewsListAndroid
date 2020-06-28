package com.newslist.news

import android.util.Log
import androidx.lifecycle.*
import com.newslist.news.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel(), LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.i("NewsViewModel", "Iniciou!" + repository.toString())
        viewModelScope.launch {
            repository.getNews().onSuccess {
                Log.i("NewsViewModel", "On sucess dentro da coroutimne!")
            }.onFailure {
                Log.i("NewsViewModel", "On error dentro da coroutimne!")
            }
        }

    }
}