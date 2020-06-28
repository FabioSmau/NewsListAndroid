package com.newslist.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.newslist.R

class NewsActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, NewsFragment.newInstance())
                    .commitNow()
        }
    }
}