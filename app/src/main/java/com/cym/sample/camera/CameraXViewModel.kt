package com.cym.sample.camera

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by caoj on 2023/6/27.
 */
class CameraXViewModel: ViewModel() {
    private val TAG = "CameraXViewModel"

    val liveData = MutableLiveData<String>()

    private var i1 = 0
    fun abc() {
        Log.i(TAG, "abc: i1: $i1")
        if (i1 == 0) {
            i1 = 999
        }
        Log.i(TAG, "abc: $this i1: $i1")
        liveData.value = "1"
    }
}