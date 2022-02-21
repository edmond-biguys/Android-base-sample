package com.cym.androidx.coordinatorlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xmcc.androidbasesample.databinding.ActivityCoordinatorLayoutSampleBinding

class CoordinatorLayoutSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCoordinatorLayoutSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener(binding)
    }

    private fun initClickListener(binding: ActivityCoordinatorLayoutSampleBinding) {
        with(binding) {
            tvBehavior.setOnClickListener {
                startActivity(Intent(this@CoordinatorLayoutSampleActivity, AboutBehaviorActivity::class.java))
            }

            tvAppBarLayout.setOnClickListener {
                startActivity(Intent(this@CoordinatorLayoutSampleActivity, AboutAppBarLayoutActivity::class.java))
            }

            tvCollapsingToolbarLayout.setOnClickListener {
                startActivity(Intent(this@CoordinatorLayoutSampleActivity, AboutCollapsingToolbarLayout::class.java))
            }
        }
    }

}