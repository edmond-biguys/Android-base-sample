package com.cym.androidx.coordinatorlayout.startmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cym.utilities.logi
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ActivityBactivityBinding

class BActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBactivityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        logi("B onCreate")
        setClickListener()
    }

    private fun setClickListener() {
        binding.button3.setOnClickListener { goToActivity(AActivity::class.java) }
        binding.button4.setOnClickListener { goToActivity(BActivity::class.java) }
        binding.button5.setOnClickListener { goToActivity(CActivity::class.java) }
        binding.button6.setOnClickListener { goToActivity(DActivity::class.java) }
    }

    private fun <T> goToActivity(cls: Class<T>) {
        startActivity(Intent(this, cls))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logi("B onNewIntent")
    }

    override fun onDestroy() {
        super.onDestroy()
        logi("B onDestroy")
    }
}