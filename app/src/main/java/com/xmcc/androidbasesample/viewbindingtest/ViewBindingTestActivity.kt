package com.xmcc.androidbasesample.viewbindingtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xmcc.androidbasesample.databinding.ActivityViewBindingTestBinding
import com.xmcc.androidbasesample.databinding.LayoutIncludeBinding
import com.xmcc.androidbasesample.databinding.LayoutIncludeMergeBinding

class ViewBindingTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBindingTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.setOnClickListener {
            println("click button2")
        }
        binding.textView.text = "abcdefg"

        println("binding.includeLayout.tvInclude ${binding.includeLayout.tvInclude.text}")

        val mergeBinding: LayoutIncludeMergeBinding = LayoutIncludeMergeBinding.bind(binding.root)
        mergeBinding.tvIncludeMerge.text = "this is include merge layout"

        binding.includeLayout.tvInclude.text = "this is include layout"

        val includeBinding: LayoutIncludeBinding = LayoutIncludeBinding.bind(binding.includeLayout.root)
        includeBinding.tvInclude.text = "${includeBinding.tvInclude.text} 1234567"

        binding.button2.setOnClickListener {
            startActivity(Intent(this, ViewBindingInAdapterActivity::class.java))
        }

    }
}