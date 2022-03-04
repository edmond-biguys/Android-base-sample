package com.cym.housedecoration.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.cym.housedecoration.bean.DecorativeMaterial
import com.xmcc.androidbasesample.databinding.ActivityDecorativeMaterialDetailBinding

class DecorativeMaterialDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDecorativeMaterialDetailBinding
    private val viewModel by viewModels<DecorativeMaterialDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDecorativeMaterialDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.requestDetail()

        setListener()
        registerObserver()

    }

    private fun registerObserver() {
        viewModel.decorativeMaterialdetailLiveData.observe(this) {
            showViewData(it)
        }
    }

    private fun setListener() {
        binding.framelayoutPayedList.setOnClickListener {
            println("to payed list")
        }

        binding.textViewDesc.setOnClickListener {  }

        binding.textViewPrice.setOnClickListener {  }

        binding.textViewPayed.setOnClickListener {  }

        binding.textViewCategory.setOnClickListener {  }

        binding.textViewDate.setOnClickListener {  }

        binding.textViewTips.setOnClickListener {  }

        binding.buttonAdd.setOnClickListener {  }
    }

    @SuppressLint("SetTextI18n")
    private fun showViewData(data: DecorativeMaterial) {
        with(data) {
            binding.toolbar.title = title//"铝合金窗户"
            binding.textViewDesc.text = desc //"铝合金窗户推拉窗（厚10.8），单价800，共60.3平"
            binding.textViewPrice.text = "总价：${totalPrice}"
            binding.textViewPayed.text = "已支付：${payedPrice}"
            binding.textViewCategory.text = "分类：${category}"
            binding.textViewDate.text = getCreateDateDisplay() //"2021-11-06"
            binding.textViewTips.text = ""
        }

    }
}