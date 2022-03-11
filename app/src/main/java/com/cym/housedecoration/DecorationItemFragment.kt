package com.cym.housedecoration

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.cym.housedecoration.bean.DecorativeMaterial
import com.tencent.bugly.proguard.I
import com.xmcc.androidbasesample.databinding.FragmentDecorationItemListBinding

/**
 * A fragment representing a list of Items.
 */
class DecorationItemFragment(private val launcher: ActivityResultLauncher<String>? = null) : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    private lateinit var binding: FragmentDecorationItemListBinding
//    private val viewModel by viewModels<HouseDecorationListViewModel>()
    private lateinit var viewModel: HouseDecorationListViewModel
    private val itemDatas = mutableListOf<DecorativeMaterial>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentDecorationItemListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(HouseDecorationListViewModel::class.java)

        with(binding) {
            list.layoutManager = LinearLayoutManager(requireContext())
            list.addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL_LIST))

            bindViewModel()

            viewModel.getDecorativeMaterialList()

            list.adapter = MyItemRecyclerViewAdapter(requireActivity(), itemDatas, launcher)

        }
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun bindViewModel() {
        viewModel.decorativeMaterialListLiveData.observe(viewLifecycleOwner) {
            itemDatas.clear()
            itemDatas.addAll(it)
            binding.list.adapter?.notifyDataSetChanged()
        }
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            DecorationItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}