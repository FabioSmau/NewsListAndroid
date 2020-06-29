package com.newslist

import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.test.AutoCloseKoinTest

abstract class KoinDependencyInjectionTest : AutoCloseKoinTest() {

    protected abstract val module: Module

    @Before
    fun before() {
        startKoin { modules(module) }
    }

}