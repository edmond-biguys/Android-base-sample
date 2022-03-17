package com.cym.housedecoration.detail.payedhistory

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ActivityPayedHostoryBinding

class PayedHostoryActivity : AppCompatActivity() {

private lateinit var binding: ActivityPayedHostoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityPayedHostoryBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}