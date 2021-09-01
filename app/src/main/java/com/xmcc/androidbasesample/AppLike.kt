package com.xmcc.androidbasesample

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.FileProvider
import androidx.multidex.MultiDex
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.tinker.entry.DefaultApplicationLike

class AppLike(application: Application, tinkerFlags: Int, tinkerLoadVerifyFlag: Boolean,
              applicationStartElapsedTime: Long, applicationStartMillisTime: Long, tinkerResultIntent: Intent):
    DefaultApplicationLike(application, tinkerFlags, tinkerLoadVerifyFlag,
        applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent){


    companion object {
        private lateinit var app: AppLike
        fun getApp(): AppLike {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        Logger.addLogAdapter(AndroidLogAdapter())
        Bugly.init(application, "4ca720fff4", true)
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    override fun onBaseContextAttached(base: Context?) {
        super.onBaseContextAttached(base)
        // you must install multiDex whatever tinker is installed
        MultiDex.install(base)

        //install tinker
        Beta.installTinker(this)

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    fun registerActivityLifecycleCallback(callBacks: Application.ActivityLifecycleCallbacks) {
        application.registerActivityLifecycleCallbacks(callBacks)

    }
}