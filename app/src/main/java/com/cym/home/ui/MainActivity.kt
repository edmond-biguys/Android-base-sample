package com.cym.home.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cym.base.BaseActivity
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ActivityMain2Binding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //badge（通知数量小红点 或者 数字）使用
        val badgeHome = binding.bottomNavigationViewHome.getOrCreateBadge(R.id.navigation_home)
        val badgeTrip = binding.bottomNavigationViewHome.getOrCreateBadge(R.id.navigation_trip)
        badgeTrip.number = 999
        badgeTrip.maxCharacterCount = 3
        badgeTrip.backgroundColor = Color.parseColor("#ffe36451")
        val badge = binding.bottomNavigationViewHome.getOrCreateBadge(R.id.navigation_user)

        /*
        把navigation和bottomNavigationView做绑定
        navigation graph中，对应fragment的id 和 bottomNavigationView中menu的id需要一样。
         */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.homeFragmentContainerView) as NavHostFragment
        binding.bottomNavigationViewHome.setupWithNavController(navHostFragment.findNavController())

    }
}