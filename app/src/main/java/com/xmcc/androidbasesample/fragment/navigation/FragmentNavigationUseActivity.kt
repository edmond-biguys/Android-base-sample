package com.xmcc.androidbasesample.fragment.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xmcc.androidbasesample.R

class FragmentNavigationUseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_navigation_use)
        println("caoj navigation activity create ${System.currentTimeMillis()}")

    }
}