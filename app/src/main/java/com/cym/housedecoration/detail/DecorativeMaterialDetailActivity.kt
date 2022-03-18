package com.cym.housedecoration.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.cym.common.widget.EditDialog
import com.cym.housedecoration.bean.DecorativeMaterial
import com.cym.housedecoration.detail.payedhistory.PayedHostoryActivity
import com.xmcc.androidbasesample.databinding.ActivityDecorativeMaterialDetailBinding

class DecorativeMaterialDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDecorativeMaterialDetailBinding
    private val viewModel by viewModels<DecorativeMaterialDetailViewModel>()

    private val gotoPayedHostoryActivity =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
                result ->
        println("resultCode: ${result.resultCode == RESULT_OK} ")
        println("resultData " +
                "${result.data?.getIntExtra(
                    ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE,
                    0)}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDecorativeMaterialDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.requestDetail()

        registerObserver()
        setListener()

    }

    private fun registerObserver() {
        viewModel.decorativeMaterialdetailLiveData.observe(this) {
            showViewData(it)
        }
    }

    private fun setListener() {
        with(binding) {
            framelayoutPayedList.setOnClickListener {
                println("to payed list")
            }

            textViewName.setOnClickListener {
                showEditDialog(toolbar.title.toString(), it as TextView)
            }

            textViewDesc.setOnClickListener {
                showEditDialog(toolbar.title.toString(), it as TextView)
            }

            textViewPrice.setOnClickListener {
                showEditDialog(toolbar.title.toString(), it as TextView) { s ->
                    println("caoj callback1111 $s")
                }
            }

            textViewPayed.setOnClickListener {
                println("goto payed hostory view")
                val intent =
                    Intent(this@DecorativeMaterialDetailActivity, PayedHostoryActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(
                    ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, 987)
                intent.putExtras(bundle)
                gotoPayedHostoryActivity.launch(intent)
            }

            textViewCategory.setOnClickListener {  }

            textViewDate.setOnClickListener {  }

            textViewTips.setOnClickListener {  }

            buttonSave.setOnClickListener {
                //todo 保存数据
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    private fun showEditDialog(title: String, contentView: TextView, method: ((s: String)->Unit)? = null) {
        val dialog = EditDialog.newInstance(title, contentView.text.toString())
        dialog.liveData.observe(this@DecorativeMaterialDetailActivity) {
            println("dialog $it")
            if (method == null) {
                contentView.text = it
            } else {
                method.invoke(it)
            }
        }
        dialog.show(supportFragmentManager, "desc")
    }

    @SuppressLint("SetTextI18n")
    private fun showViewData(data: DecorativeMaterial) {
        with(data) {
            binding.toolbar.title = title//"铝合金窗户"
            binding.textViewName.text = title//"铝合金窗户"
            binding.textViewDesc.text = desc //"铝合金窗户推拉窗（厚10.8），单价800，共60.3平"
            binding.textViewPrice.text = "总价：${totalPrice}"
            binding.textViewPayed.text = "已支付：${totalPayedPrice}"
            binding.textViewCategory.text = "分类：${category}"
            binding.textViewDate.text = getCreateDateDisplay() //"2021-11-06"
            binding.textViewTips.text = ""
        }
    }

    companion object {
        fun startDecorativeMaterialDetailActivity(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }
}