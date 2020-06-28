package com.newslist.news

import com.newslist.base.DispatcherDefaultProvider
import com.newslist.base.DispatcherProvider
import com.newslist.news.repository.NewsAPI
import com.newslist.news.repository.NewsRemoteRepository
import com.newslist.news.repository.NewsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val newsModule = module {
    viewModel { NewsViewModel(get()) }
    single<NewsRepository> {
        NewsRemoteRepository(
            get(),
            get()
        )
    }
    factory { provideNewsAPI(get()) }
    single<DispatcherProvider> { DispatcherDefaultProvider() }
}

fun provideNewsAPI(retrofit: Retrofit): NewsAPI = retrofit.create(NewsAPI::class.java)

