package com.cym.housedecoration.detail.payedhistory

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.xmcc.androidbasesample.databinding.ActivityPayedHostoryBinding

class PayedHostoryActivity : AppCompatActivity() {

private lateinit var binding: ActivityPayedHostoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityPayedHostoryBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finishView(true)
            }
            else -> {
                println("do nothing")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finishView()
    }

    private fun finishView(needUpdate: Boolean = false) {
        if (!needUpdate) {
            finish()
            return
        }
        val bundle = Bundle()
        bundle.putInt(
            ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, 11111)
        intent.putExtras(bundle)
        setResult(RESULT_OK, intent)
        finish()
    }
}