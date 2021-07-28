package com.xmcc.androidbasesample.viewbindingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ActivityViewBindingInAdapterBinding
import com.xmcc.androidbasesample.databinding.LayoutItemViewbindingInAdapterBinding

class ViewBindingInAdapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBindingInAdapterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingInAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = Adapter(listOf("first", "second", "third"))
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

    }

    class Adapter(val items: List<String>): RecyclerView.Adapter<Adapter.ViewHolder>() {

        class ViewHolder(binding: LayoutItemViewbindingInAdapterBinding, itemView: View):
            RecyclerView.ViewHolder(itemView) {
            val textView: TextView = binding.textView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = LayoutItemViewbindingInAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_viewbinding_in_adapter, parent, false)
            return ViewHolder(binding, binding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = items[position]
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }
}

