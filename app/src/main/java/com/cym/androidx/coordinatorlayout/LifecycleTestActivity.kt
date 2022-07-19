package com.cym.androidx.coordinatorlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cym.utilities.logi
import com.xmcc.androidbasesample.R

class LifecycleTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_test)
        savedInstanceState?.let {
            val bundle = it.getBundle("abc")
            logi("create bundle value ${bundle?.getString("name")}")
        }
        logi("onCreate $savedInstanceState")
    }

    override fun onStart() {
        super.onStart()
        logi("onStart")
    }

    override fun onResume() {
        super.onResume()
        logi("onResume")
    }

    override fun onPause() {
        super.onPause()
        logi("onPause")
    }

    override fun onStop() {
        super.onStop()
        logi("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logi("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logi("onSaveInstanceState $outState")
        val bundle = Bundle()
        bundle.putString("name", "kid")
        outState.putBundle("abc", bundle)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logi("onRestoreInstanceState $savedInstanceState")
        val bundle = savedInstanceState.getBundle("abc")
        logi("${bundle?.getString("name")}")
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        logi("onTopResumedActivityChanged $isTopResumedActivity")
    }
}