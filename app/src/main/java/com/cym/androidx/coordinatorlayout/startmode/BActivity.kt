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
        logi("B onCreate ${this.taskId}")
        setClickListener()
    }

    private fun setClickListener() {
        binding.button3.setOnClickListener {
//            goToActivity(AActivity::class.java)
            val intent = Intent(this, AActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            //清理要跳转的activity之上的所有activity，taskId 不变
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            //清理这个task内所有activity（包括要跳转的activity），taskId不变
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        binding.button4.setOnClickListener { goToActivity(BActivity::class.java) }
        binding.button5.setOnClickListener { goToActivity(CActivity::class.java) }
        binding.button6.setOnClickListener { goToActivity(DActivity::class.java) }
    }

    private fun <T> goToActivity(cls: Class<T>) {
        val intent = Intent(this, cls)
//        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
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