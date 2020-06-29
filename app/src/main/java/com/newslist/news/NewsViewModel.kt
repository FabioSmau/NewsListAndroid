package com.newslist.news

import androidx.lifecycle.*
import com.newslist.news.repository.Article
import com.newslist.news.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel(), LifecycleObserver {

    private val viewState = MutableLiveData<ViewAction>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadNews() {
        viewModelScope.launch {
            viewState.value = ViewAction.Loading
            repository.getNews().onSuccess {
                viewState.value = ViewAction.NewsLoaded(it.articles)
            }.onFailure {
                viewState.value = ViewAction.Error
            }
        }
    }

    fun getViewState(): LiveData<ViewAction> = viewState

    sealed class ViewAction {
        object Loading : ViewAction()
        object Error: ViewAction()
        class NewsLoaded(val articles: List<Article>) : ViewAction()
    }
}