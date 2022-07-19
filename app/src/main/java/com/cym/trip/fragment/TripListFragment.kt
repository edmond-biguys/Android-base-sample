package com.cym.trip.fragment

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cym.androidx.coordinatorlayout.CoordinatorLayoutSampleActivity
import com.cym.androidx.coordinatorlayout.LifecycleTestActivity
import com.cym.androidx.coordinatorlayout.viewpager2.ViewPager2SampleActivity
import com.cym.jetpack.workmanager.WorkManagerSampleActivity
import com.cym.sunflower.GardenActivity
import com.xmcc.androidbasesample.databinding.ItemFunctionListBinding
import com.xmcc.androidbasesample.databinding.TripListFragmentBinding

class TripListFragment : Fragment() {

    companion object {
        fun newInstance() = TripListFragment()
    }

    private lateinit var viewModel: TripListViewModel

    private lateinit var binding: TripListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TripListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("TripListFragment onViewCreated")
        viewModel = ViewModelProvider(requireActivity()).get(TripListViewModel::class.java)
        createRecyclerView()
    }

    private fun createRecyclerView() {
        with(binding) {
            recyclerView01.layoutManager = LinearLayoutManager(requireContext())

            val itemDataList = mutableListOf(
                "WorkManager", "ViewBinding", "Garden",
                "CoordinatorLayout", "ViewPager2", "Lifecycle Test"
            )

            recyclerView01.adapter = MyAdapter(itemDataList, requireContext())
        }
    }

    class MyAdapter(val items: MutableList<String>, val context: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        private lateinit var itemBinding: ItemFunctionListBinding

        class MyViewHolder(binding: ItemFunctionListBinding, view: View): RecyclerView.ViewHolder(view) {
            val title = binding.textViewFunction
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            itemBinding = ItemFunctionListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(itemBinding, itemBinding.root)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.title.text = items[position]
            holder.itemView.setOnClickListener {
                println("click ${items[position]}")
                when {
                    items[position] == "WorkManager" -> navigateWorkManagerSampleView(context)
                    items[position] == "Garden" -> navigateGardenActivity(context)
                    items[position] == "CoordinatorLayout" -> navigateCoordinatorLayoutSampleActivity(context)
                    items[position] == "ViewPager2" -> navigateViewPager2Activity(context)
                    items[position] == "Lifecycle Test" -> navigateLifecycleActivity(context)
                    else -> println("do nothing")
                }

            }
        }

        private fun navigateWorkManagerSampleView(context: Context) {
            context.startActivity(Intent(context, WorkManagerSampleActivity::class.java))
        }

        private fun navigateGardenActivity(context: Context) {
            context.startActivity(Intent(context, GardenActivity::class.java))
        }

        private fun navigateCoordinatorLayoutSampleActivity(context: Context) {
            context.startActivity(Intent(context, CoordinatorLayoutSampleActivity::class.java))
        }

        private fun navigateViewPager2Activity(context: Context) {
            context.startActivity(Intent(context, ViewPager2SampleActivity::class.java))
        }

        private fun navigateLifecycleActivity(context: Context) {
            context.startActivity(Intent(context, LifecycleTestActivity::class.java))
        }


        override fun getItemCount(): Int {
            return items.size
        }
    }

}
