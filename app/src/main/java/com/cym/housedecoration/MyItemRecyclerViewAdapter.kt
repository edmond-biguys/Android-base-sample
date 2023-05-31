package com.cym.housedecoration

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.FragmentActivity
import com.cym.housedecoration.bean.DecorativeMaterial
import com.cym.housedecoration.detail.DecorativeMaterialDetailActivity

import com.cym.housedecoration.placeholder.PlaceholderContent.PlaceholderItem
import com.xmcc.androidbasesample.databinding.FragmentDecorationItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val context: Context,
    private val values: List<DecorativeMaterial>,
    private val launcher: ActivityResultLauncher<String>? = null
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentDecorationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.title.text = item.title
        holder.category.text = item.category
        holder.price.text = "总价：${item.getTotalPriceDisplay()}"
        holder.desc.text = item.desc
        holder.date.text = item.getCreateDateDisplay()




        holder.itemView.setOnClickListener {
            println("caoj click itemView $position")
            launcher?.launch("abc")
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentDecorationItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        val icon: ImageView = binding.imageViewIcon
        val title: TextView = binding.textViewTitle
        val category: TextView = binding.textViewCategory
        val price: TextView = binding.textViewPrice
        val desc: TextView = binding.textViewDesc
        val date: TextView = binding.textViewDate


        override fun toString(): String {
            println("values.size ${values.size}")
            return ""
        }
    }

}