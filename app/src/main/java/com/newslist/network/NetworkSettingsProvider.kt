package com.newslist.network

interface NetworkSettingsProvider<T> {
    fun provide(): T
}