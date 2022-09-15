package com.cym.sample.flow

import com.cym.utilities.logi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class NewsRepository(
    private val newsRemoteDataSource: NewsRemoteDataSource,

) {
    val favoriteLastestNews: Flow<List<String>> =
        newsRemoteDataSource.lastestNews
            .map { news -> news.filter { item -> isFavoriteNews(item) } }
            .onEach {
                news -> saveInCache(news)
            }

    private fun isFavoriteNews(news: String): Boolean {
        logi("filter news $news")
        return news.startsWith("a")
    }
    private fun saveInCache(news: List<String>) {
        logi("cache $news")
    }
}