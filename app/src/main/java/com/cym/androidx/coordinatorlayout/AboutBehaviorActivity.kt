package com.cym.androidx.coordinatorlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.xmcc.androidbasesample.databinding.ActivityAboutBehaviorBinding

/**
 * custom behavior
 */
class AboutBehaviorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBehaviorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBehaviorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            //被观察者1
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

            //被观察者2
            buttonObserved2.setOnClickListener {  }
            buttonObserved2.setOnTouchListener { v, event ->

                when(event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX2 = event.rawX
                        lastY2 = event.rawY
                        return@setOnTouchListener true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        buttonMove(v, event, observer = 2)
                        lastX2 = event.rawX
                        lastY2 = event.rawY
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

    private var lastX2 = 0f
    private var lastY2 = 0f

    private fun buttonMove(view: View, event: MotionEvent, observer: Int = 1) {

        if (observer == 2) {
            view.x = view.x + event.rawX - lastX2
            view.y = view.y + event.rawY - lastY2
        } else {
            view.x = view.x + event.rawX - lastX
            view.y = view.y + event.rawY - lastY
        }

    }
}