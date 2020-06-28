package com.newslist.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide
            .with(this.context)
            .load(it)
            .centerCrop()
            .error(android.R.drawable.stat_notify_error)
            .into(this)
    }
}