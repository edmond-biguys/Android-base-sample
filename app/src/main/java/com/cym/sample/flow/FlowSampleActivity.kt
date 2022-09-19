package com.cym.sample.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.cym.utilities.logi
import com.xmcc.androidbasesample.databinding.ActivityFlowSampleBinding

class FlowSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlowSampleBinding
    private val viewModel by viewModels<LastNewsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logi("onCreate")
        binding = ActivityFlowSampleBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        viewModel.start()




    }
}