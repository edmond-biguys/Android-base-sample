package com.cym.user.fragment

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(context: Context, workerParams: WorkerParameters):
    Worker(context, workerParams) {
    companion object {
        const val TAG = "UploadWorker"
    }
    override fun doWork(): Result {
        uploadImage()
        return Result.success()
    }

    private fun uploadImage() {
        Log.i(TAG, "uploadImage: ${Thread.currentThread()}")
        //Thread.sleep(60_000)
        Thread {
            //Thread.sleep(60_000)
            Log.i(TAG, "uploadImage: thread ${Thread.currentThread()}")
        }.start()
    }
}