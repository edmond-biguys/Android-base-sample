package com.cym.sample.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cym.sample.flow.bean.AAndQ
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

    //stateFlow使用 begin
    //stateFlow使用 end

    fun start() {
       logi("LastNewsViewModel start")
    }
}

sealed class ExaminationQuestionsUiState {
    data class Success(val questions: List<AAndQ>): ExaminationQuestionsUiState()
    data class Error(val exception: Throwable): ExaminationQuestionsUiState()
}