package com.cym.androidx.coordinatorlayout.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

class CustomButton(
    context: Context,
    attributeSet: AttributeSet? = null
): AppCompatButton(context, attributeSet) {

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        return when(event.action) {
            MotionEvent.ACTION_DOWN -> true
            MotionEvent.ACTION_UP -> {
                println("edmond action up in custom button")
                performClick()
                true
            }
            else -> false
        }
    }

    override fun performClick(): Boolean {
        super.performClick()
        //do something
        return true
    }
}