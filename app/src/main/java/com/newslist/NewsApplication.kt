package com.newslist

import android.app.Application
import com.newslist.network.networkingModule
import com.newslist.news.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@NewsApplication)
            androidLogger()
            modules(networkingModule, newsModule)
        }
    }

}