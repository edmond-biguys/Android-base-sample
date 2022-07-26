package com.cym.androidx.coordinatorlayout.startmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cym.utilities.logi
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ActivityDactivityBinding

class DActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDactivityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        logi("D onCreate")
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
        logi("D onNewIntent")
    }

    override fun onDestroy() {
        super.onDestroy()
        logi("D onDestroy")
    }
}