package com.cym.sample.persistence

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xmcc.androidbasesample.databinding.ActivityPersistenceSampleBinding

class PersistenceSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersistenceSampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersistenceSampleBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        clickListenerInit()
    }

    private fun clickListenerInit() {
        binding.buttonDataStore.setOnClickListener {
            startActivity(Intent(this, DataStoreActivity::class.java))
        }
    }
}