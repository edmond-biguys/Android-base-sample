package com.cym.sample.flow

import com.cym.utilities.logi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class NewsRepository(
    newsRemoteDataSource: NewsRemoteDataSource,
) {
    val favoriteLastNews: Flow<List<String>> =
        newsRemoteDataSource.lastNews
            .map { news -> news.filter { item -> isFavoriteNews(item) } }
            .onEach {
                news -> saveInCache(news)
            }

    private fun isFavoriteNews(news: String): Boolean {
        logi("filter news $news")
        return news.startsWith("a")
    }

    private val flowTest: Flow<String> = flow {
        while (true) {
            emit("abc")
            delay(5000)
        }
    }

    suspend fun testFlow() {
        flowTest.withIndex().collect{
            println("index ${it.index} value ${it.value}")
        }
    }

    private fun saveInCache(news: List<String>) {
        logi("cache $news")
    }
}