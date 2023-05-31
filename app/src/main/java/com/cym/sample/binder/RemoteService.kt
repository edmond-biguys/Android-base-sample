package com.cym.sample.binder

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Parcel
import android.util.Log

class RemoteService : Service() {
    companion object {
        const val TAG = "RemoteService"
    }
    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: ")
        //sendBroadcast()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    private val mBinder: IMyAidlRemote.Stub = object : IMyAidlRemote.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
            Log.i(TAG, "basicTypes: ")
        }

        override fun getUid(): Int {
            Log.i(TAG, "getUid: ")
            return 2
        }

        override fun getUser(uid: Int): User {
            Log.i(TAG, "getUser: ")
            return User("name$uid", uid + 10)
        }



    }
}