package com.cym.housedecoration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xmcc.androidbasesample.databinding.ActivityHouseDecorationListBinding

class HouseDecorationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHouseDecorationListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseDecorationListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}