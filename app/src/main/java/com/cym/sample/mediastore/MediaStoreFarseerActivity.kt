package com.cym.sample.mediastore

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.xmcc.androidbasesample.databinding.ActivityMediaStoreFarseerBinding

private const val TAG = "MediaStoreFarseer"
class MediaStoreFarseerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaStoreFarseerBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMediaStoreFarseerBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        checkPermission()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermission() {
        val granted = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES)
        Log.i(TAG, "checkPermission: ${granted == PackageManager.PERMISSION_GRANTED}")
        if (granted != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i(TAG, "onActivityResult: $requestCode $resultCode $data")
    }
}