package com.xmcc.androidbasesample.fragment.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xmcc.androidbasesample.R

class TestDynamicSchemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dynamic_scheme)
        println("caoj -------------------")
        println(intent)
        println(intent.data)
        println(intent.dataString)
    }
}