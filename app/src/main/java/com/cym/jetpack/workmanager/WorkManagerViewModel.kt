package com.cym.jetpack.workmanager

import android.app.Application
import android.content.Context
import android.view.Choreographer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest

class WorkManagerViewModel(application: Application) : AndroidViewModel(application) {

    val workResultState by mutableStateOf(false)
    fun start(context: Context) {
        val workRequest: WorkRequest = OneTimeWorkRequestBuilder<FooWork>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

}