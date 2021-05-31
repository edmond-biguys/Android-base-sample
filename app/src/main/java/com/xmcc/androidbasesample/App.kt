package com.xmcc.androidbasesample

import android.app.Application
import android.content.Intent
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.xmcc.androidbasesample.device.bluetooth.BluetoothService


/**
 * Created by caoj on 2021/5/28.
 */
class App: Application() {


    companion object {
        private lateinit var app: App
        fun getApp(): App {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        Logger.addLogAdapter(AndroidLogAdapter())
        startService(Intent(this, BluetoothService::class.java))
    }
}