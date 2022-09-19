package com.cym.sample.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cym.utilities.logi
import kotlinx.coroutines.launch

class LastNewsViewModel
//    (
//    private val newsRepository: NewsRepository
//    )
    : ViewModel() {

    private val newsRepository: NewsRepository
        init {
            logi("LastNewsViewModel init")
            newsRepository = NewsRepository(NewsRemoteDataSource())
            viewModelScope.launch {
                newsRepository.favoriteLastNews.collect {
                    favoriteNews ->
                    logi("favoriteNews $favoriteNews")
                }
            }

            viewModelScope.launch {
                newsRepository.testFlow()
            }
        }

    fun start() {
       logi("LastNewsViewModel start")
    }
}