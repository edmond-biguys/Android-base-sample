package com.cym.user.fragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.work.*
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class UserViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    companion object {
        const val TAG = "UserViewModel"
    }
    fun startWorkManager(context: Context) {
        Log.i(TAG, "startWorkManager: ${Thread.currentThread()}")
        Thread {
            Log.i(TAG, "startWorkManager: thread ${Thread.currentThread()}")
            Thread {
                Log.i(TAG, "startWorkManager: thread22 ${Thread.currentThread()}")
            }.start()
        }.start()
        //1. 创建workRequest
        val uploadWorkerRequest: WorkRequest =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .build()

        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<UploadWorker>(30, TimeUnit.SECONDS)
                .build()
        //2. 将workRequest提交给系统
//        WorkManager.getInstance(context).enqueue(uploadWorkerRequest)
        WorkManager.getInstance(context).enqueue(periodicWorkRequest)
    }

    fun test() {
        val m = {
            println("fff")
            //return
        }
        foo({
            println("abc")
            //return
        }, m)
        println("test end")

        foo3 { println("foo3") }

    }


    inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
        inlined()
        notInlined()
    }

    inline fun foo2(noinline notInlined: () -> Unit) {

    }

    inline fun foo3(crossinline crossinlined: () -> Unit) {
        val f = Runnable {
            crossinlined()
            return@Runnable
        }
    }

}