package com.cym.androidx.coordinatorlayout

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xmcc.androidbasesample.databinding.ActivityAboutCollapsingtoolbarlayoutBinding
import com.xmcc.androidbasesample.databinding.ItemAboutCollapsingtoolbarlayoutBinding

class AboutCollapsingToolbarLayout: AppCompatActivity() {

    private lateinit var binding: ActivityAboutCollapsingtoolbarlayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val decorView = window.decorView
//            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//            window.statusBarColor = Color.TRANSPARENT
//            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//
//        }

        StatusBarUtil.setTranslucent(this)

//        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        super.onCreate(savedInstanceState)
        binding = ActivityAboutCollapsingtoolbarlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.toolbar.titleMarginBottom = -50

        val recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val adapter = MyAdapter(mockItemdata())
        recyclerView.adapter = adapter


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
//        hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun mockItemdata(): List<String> {
        val list = mutableListOf<String>()
        for (index in 0..20) {
            list.add("this is item $index")
        }
        return list
    }


    class MyAdapter(private val itemDatas: List<String>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {


        class ViewHolder(
            binding: ItemAboutCollapsingtoolbarlayoutBinding): RecyclerView.ViewHolder(binding.root) {
                val title = binding.textView
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemAboutCollapsingtoolbarlayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.text = itemDatas[position]
        }

        override fun getItemCount(): Int {
            return itemDatas.size
        }
    }
}