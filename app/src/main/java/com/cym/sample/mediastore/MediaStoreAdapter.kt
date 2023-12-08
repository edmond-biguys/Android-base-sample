package com.cym.sample.mediastore

import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ItemMediaStoreListBinding

/**
 * Created by caoj on 2023/11/27.
 */
class MediaStoreAdapter(private val itemDataList: List<MediaStoreItem>,
                        private val itemClick: ((position: Int, media: MediaStoreItem)->Unit)? = null):
    RecyclerView.Adapter<MediaStoreAdapter.MyHolder>() {
    private lateinit var binding: ItemMediaStoreListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        binding = ItemMediaStoreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder()
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        if (position == 0) {
            holder.imageView.setImageResource(R.drawable.ic_rectagle_01)
            holder.imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        } else if (itemDataList[position].bmp != null) {
            holder.imageView.setImageBitmap(itemDataList[position].bmp)
            holder.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            holder.imageView.setImageURI(itemDataList[position].uri)
            holder.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        holder.flRoot.setOnClickListener {
            itemClick?.invoke(position, itemDataList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemDataList.size
    }

    inner class MyHolder: ViewHolder(binding.root) {
        val imageView = binding.ivMediaStore
        val flRoot = binding.flRoot
    }
}

data class MediaStoreItem(
    val uri: Uri,
    val bmp: Bitmap? = null,
    val id: Long = 0,
    val name: String = "",
    val duration: Int = 0,
    val size: Int = 0
)

