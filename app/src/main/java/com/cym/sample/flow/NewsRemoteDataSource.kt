package com.cym.sample.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRemoteDataSource(
    private val newsApi: NewsApi? = null,
    private val refreshIntervalMs: Long = 5000
) {

    val lastNews: Flow<List<String>> = flow {
        while (true) {

            if (newsApi == null) {
                val list = mutableListOf<String>()
                list.add("a")
                list.add("ab")
                list.add("c")
                emit(list)
                delay(refreshIntervalMs)
                continue
            }

            val lastNews = newsApi.fetchLastestNews()
            emit(lastNews)


            delay(refreshIntervalMs)
        }
    }
}

interface NewsApi {
    suspend fun fetchLastestNews(): List<String>
}
