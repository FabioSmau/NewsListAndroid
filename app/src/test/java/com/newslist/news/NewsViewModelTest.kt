package com.newslist.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.newslist.KoinDependencyInjectionTest
import com.newslist.news.repository.NewsRepository
import com.newslist.news.repository.NewsResponse
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.inject
import org.koin.dsl.module


class NewsViewModelTest : KoinDependencyInjectionTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: NewsRepository
    private lateinit var mockObserver: Observer<NewsViewModel.ViewAction>
    private val testDispatcher = TestCoroutineDispatcher()

    private val viewModel: NewsViewModel by inject()

    override val module = module {
        repository = mockk(relaxed = true)
        viewModel { NewsViewModel(repository) }
    }

    @Before
    fun setUp() {
        mockObserver = mockk(relaxed = true)
        viewModel.getViewState().observeForever(mockObserver)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun when_repository_return_error_then_setup_loading_and_error_state() {
        setupErrorRepositoryMock()
        viewModel.loadNews()
        verify {
            mockObserver.onChanged(NewsViewModel.ViewAction.Loading)
            mockObserver.onChanged(NewsViewModel.ViewAction.Error)
        }
    }

    @Test
    fun when_repository_return_success_then_setup_loading_and_loaded_state() {
        setupSuccessRepositoryMock()
        viewModel.loadNews()
        verify {
            mockObserver.onChanged(NewsViewModel.ViewAction.Loading)
            mockObserver.onChanged(NewsViewModel.ViewAction.NewsLoaded(any()))
        }
    }

    private fun setupSuccessRepositoryMock() {
        val response = mockk<NewsResponse>(relaxed = true)
        coEvery { repository.getNews() } returns Result.success(response)
    }

    private fun setupErrorRepositoryMock() {
        coEvery { repository.getNews() } returns Result.failure(mockk(relaxed = true))
    }

}