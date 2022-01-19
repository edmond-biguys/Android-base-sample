package com.cym.jetpack.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class FooWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        println("caoj FooWork ${Thread.currentThread()}")
        return Result.success()
    }
}