package com.cym.housedecoration

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.xmcc.androidbasesample.databinding.FragmentDecorationItemListBinding

/**
 * A fragment representing a list of Items.
 */
class DecorationItemFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private lateinit var binding: FragmentDecorationItemListBinding
    private val viewModel by viewModels<HouseDecorationListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDecorationItemListBinding.inflate(inflater, container, false)

        with(binding) {
            list.layoutManager = LinearLayoutManager(context)
            list.addItemDecoration(
                com.cym.housedecoration.DividerItemDecoration(
                    context, com.cym.housedecoration.DividerItemDecoration.VERTICAL_LIST))
            list.adapter = MyItemRecyclerViewAdapter(viewModel.getDecorativeMaterialList())
        }
        return binding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            DecorationItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}