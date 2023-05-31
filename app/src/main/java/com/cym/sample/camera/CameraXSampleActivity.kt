package com.cym.sample.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.ImageCapture
import com.xmcc.androidbasesample.databinding.ActivityCameraXsampleBinding


class CameraXSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraXsampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraXsampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}