package com.cym.sample.mediastore

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.xmcc.androidbasesample.databinding.ActivityMediaStoreFarseerBinding

private const val TAG = "MediaStoreFarseer"
class MediaStoreFarseerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaStoreFarseerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMediaStoreFarseerBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        checkPermission()
    }

    private fun checkPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            android.Manifest.permission.READ_MEDIA_IMAGES
        } else {
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        }
        val granted = ContextCompat.checkSelfPermission(this, permission)
        Log.i(TAG, "checkPermission: ${granted == PackageManager.PERMISSION_GRANTED}")
        if (granted != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "onRequestPermissionsResult: $requestCode $permissions $grantResults")
    }
}