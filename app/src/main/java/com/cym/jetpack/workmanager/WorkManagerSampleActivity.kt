package com.cym.jetpack.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat

class WorkManagerSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
//        setContent {
//            ProvideWindowInsets(consumeWindowInsets = false) {
//                val systemUiController = rememberSystemUiController()
//                SideEffect {
//                    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false)
//                }
//                Column {
//                    Spacer(modifier = Modifier.statusBarsHeight().fillMaxWidth())
//                    WorkManagerComposeView()
//                }
//            }
//        }
    }
}