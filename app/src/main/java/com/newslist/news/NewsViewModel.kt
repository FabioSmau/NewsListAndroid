package com.newslist.news

import androidx.lifecycle.*
import com.newslist.news.repository.Article
import com.newslist.news.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel(), LifecycleObserver {

    private val viewAction = MutableLiveData<ViewAction>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start() {
        viewAction.value = ViewAction.Loading
        viewModelScope.launch {
            repository.getNews().onSuccess {
                viewAction.value = ViewAction.NewsLoaded(it.articles)
            }.onFailure {
                viewAction.value = ViewAction.NewsLoaded(emptyList())
            }
        }
    }

    fun observeViewAction(owner: LifecycleOwner, observer: Observer<ViewAction>) = viewAction.observe(owner, observer)

    sealed class ViewAction {
        object Loading : ViewAction()
        class NewsLoaded(val articles: List<Article>) : ViewAction()
    }
}