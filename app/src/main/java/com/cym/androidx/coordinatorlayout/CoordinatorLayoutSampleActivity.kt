package com.cym.androidx.coordinatorlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.xmcc.androidbasesample.databinding.ActivityCoordinatorLayoutSampleBinding

class CoordinatorLayoutSampleActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCoordinatorLayoutSampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorLayoutSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        CoordinatorLayout
//        AppBarLayout
//        CollapsingToolbarLayout

        with(binding) {
            buttonObserved.setOnClickListener {  }
            buttonObserved.setOnTouchListener { v, event ->

                when(event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX = event.rawX
                        lastY = event.rawY
                        return@setOnTouchListener true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        buttonMove(v, event)
                        lastX = event.rawX
                        lastY = event.rawY
                        return@setOnTouchListener true
                    }
                    MotionEvent.ACTION_UP -> {
                        v.performClick()
                        println("edmond action up in child")
                        return@setOnTouchListener true
                    }
                    else -> return@setOnTouchListener false
                }
            }
        }
    }
    private var lastX = 0f
    private var lastY = 0f
    private fun buttonMove(view: View, event: MotionEvent) {
        view.x = view.x + event.rawX - lastX
        view.y = view.y + event.rawY - lastY
    }
}