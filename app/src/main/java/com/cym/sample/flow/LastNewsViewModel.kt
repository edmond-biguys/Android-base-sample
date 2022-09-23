package com.cym.sample.flow

import androidx.lifecycle.MutableLiveData
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

    //liveData使用 begin
    private val _testLiveData = MutableLiveData<String>()


    //liveData使用 end

    //stateFlow使用 begin
    //stateFlow使用 end

    fun start() {
       logi("LastNewsViewModel start")
    }
}