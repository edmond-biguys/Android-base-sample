package com.xmcc.androidbasesample.fragment.navigation.deeplink

import android.app.PendingIntent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDeepLinkBuilder
import com.xmcc.androidbasesample.R

class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link)

//        val pendingIntent: PendingIntent = NavDeepLinkBuilder(this)
//            .setGraph(R.navigation.nav_deeplink)
//            .setDestination(R.id.action_first2Fragment_to_second2Fragment)
//            .createPendingIntent()
//        startActivity(pendingIntent)

    }
}