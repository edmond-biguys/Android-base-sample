package com.cym.androidx.coordinatorlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.xmcc.androidbasesample.databinding.ActivityAboutAppbarlayoutBinding
import com.xmcc.androidbasesample.databinding.ItemAboutAppbarlayoutRecyclerviewBinding

class AboutAppBarLayoutActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAboutAppbarlayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutAppbarlayoutBinding.inflate(layoutInflater)

        //AppBarLayout
        setContentView(binding.root)

        with(binding) {
            toolbar.title = "AppBarLayout--"
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = MyAdapter(mockItemsData(20))

        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        binding.recyclerView.adapter = adapter


    }

    private fun mockItemsData(count: Int = 5): List<String> {

        val list = mutableListOf<String>()
        for (index in 0..count) {
            list.add("this item $index")
        }

        return list
    }

    class MyAdapter(private val items: List<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val binding = ItemAboutAppbarlayoutRecyclerviewBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.title.text = items[position]
        }

        override fun getItemCount(): Int {
            return items.size
        }

        class MyViewHolder(binding: ItemAboutAppbarlayoutRecyclerviewBinding):
            RecyclerView.ViewHolder(binding.root) {
            val title = binding.tvTitle
        }

    }
}