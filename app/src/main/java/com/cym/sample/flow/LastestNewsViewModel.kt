package com.cym.sample.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cym.utilities.logi
import kotlinx.coroutines.launch

class LastestNewsViewModel(
    private val newsRepository: NewsRepository
    ): ViewModel() {

        init {
            viewModelScope.launch {
                newsRepository.favoriteLastestNews.collect {
                    favoriteNews ->
                    logi("favoriteNews $favoriteNews")
                }
            }
        }
}