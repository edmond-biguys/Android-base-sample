package com.xmcc.androidbasesample.fragment.navigation.nestedgraph

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.xmcc.androidbasesample.R

import com.xmcc.androidbasesample.fragment.navigation.nestedgraph.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<DummyItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_nested_graph_root, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        when(position) {
            0 -> {
                holder.contentView.text = "goto order"
            }
            1 -> {
                holder.contentView.text = "goto user"
            }
            2 -> {
                holder.contentView.text = "navigateUp"
            }
            3 -> {
                holder.contentView.text = "popStack"
            }
            else -> holder.contentView.text = item.content
        }
        holder.itemView.setOnClickListener {
            when(position) {
                0 -> {
                   Navigation.findNavController(it).navigate(R.id.action_nestedGraphRootFragment_to_orderListFragment)
                }
                1 -> {
                    Navigation.findNavController(it).navigate(R.id.action_nestedGraphRootFragment_to_userHomeFragment)
                }
                2 -> {
                    val ret = Navigation.findNavController(it).navigateUp()
                    val controller = Navigation.findNavController(it)
                    println("caoj navigateUp $ret")
                }
                3 -> {
                    val ret = Navigation.findNavController(it).popBackStack()
                    val controller = Navigation.findNavController(it)
                    println("caoj popStack $ret")
                }
                else -> println("do nothing")
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}