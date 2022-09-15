package com.cym.sample.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRemoteDataSource(
    private val newsApi: NewsApi,
    private val refreshIntervalMs: Long = 5000
) {

    val lastestNews: Flow<List<String>> = flow {
        while (true) {
            val lastestNews = newsApi.fetchLastestNews()
            emit(lastestNews)
            delay(refreshIntervalMs)
        }
    }

}

interface NewsApi {
    suspend fun fetchLastestNews(): List<String>
}
