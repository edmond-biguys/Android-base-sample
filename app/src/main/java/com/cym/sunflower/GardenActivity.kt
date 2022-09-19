package com.cym.sunflower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.xmcc.androidbasesample.databinding.ActivityGardenBinding

class GardenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(ActivityGardenBinding.inflate(layoutInflater).root)
    }
}