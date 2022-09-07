package com.cym.androidx.coordinatorlayout.startmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cym.utilities.logi
import com.xmcc.androidbasesample.databinding.ActivityAactivityBinding

class AActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAactivityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setClickListeners()
        logi("A onCreate ${this.taskId}")
    }

    override fun onDestroy() {
        super.onDestroy()
        logi("A onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logi("A onNewIntent")
    }

    private fun setClickListeners() {
        binding.button3.setOnClickListener {
            goToActivity(AActivity::class.java)
//            val intent = Intent(this, AActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//            startActivity(intent)
        }
        binding.button4.setOnClickListener { goToActivity(BActivity::class.java) }
        binding.button5.setOnClickListener { goToActivity(CActivity::class.java) }
        binding.button6.setOnClickListener { goToActivity(DActivity::class.java) }
    }


    private fun <T> goToActivity(cls: Class<T>) {
        startActivity(Intent(this, cls))
    }
}