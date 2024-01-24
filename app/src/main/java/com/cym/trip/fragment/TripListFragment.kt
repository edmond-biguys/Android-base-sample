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
import com.cym.androidx.coordinatorlayout.startmode.AActivity
import com.cym.androidx.coordinatorlayout.viewpager2.ViewPager2SampleActivity
import com.cym.jetpack.workmanager.WorkManagerSampleActivity
import com.cym.sample.camera.CameraXSampleActivity
import com.cym.sample.contentprovider.ContentProviderSampleActivity
import com.cym.sample.download.FileDownloadActivity
import com.cym.sample.flow.FlowSampleActivity
import com.cym.sample.mediastore.MediaStoreFarseerActivity
import com.cym.sample.persistence.PersistenceSampleActivity
import com.cym.sample.storage.StorageTestActivity
import com.cym.sample.threadtest.ThreadTestActivity
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
        viewModel = ViewModelProvider(requireActivity())[TripListViewModel::class.java]
        createRecyclerView()
    }

    private fun createRecyclerView() {
        with(binding) {
            recyclerView01.layoutManager = LinearLayoutManager(requireContext())

            val itemDataList = mutableListOf(
                "WorkManager", "ViewBinding", "Garden",
                "CoordinatorLayout", "ViewPager2", "Lifecycle Test", "Start Mode",
                "Persistence", "FlowSample", "判断、选择题", "名词解释", "判断题-否",
                "ContentProvider","CameraX", "MediaStore", "StorageTest", "DownloadTest",
                "ThreadTest","ThreadTest","ThreadTest"
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
                    items[position] == "Start Mode" -> navigateStartModelActivity(context)
                    items[position] == "Persistence" -> navigatePersistenceActivity(context)
                    items[position] == "FlowSample" -> navigateFlowSampleActivity(context)
                    items[position] == "判断、选择题" -> navigateQuestionActivity(context, "q1", onlyYes = true)
                    items[position] == "名词解释" -> navigateQuestionActivity(context, "q2")
                    items[position] == "判断题-否" -> navigateQuestionActivity(context, "q1", onlyPanduan = true)
                    items[position] == "ContentProvider" -> navigateContentProviderSampleActivity(context)
                    items[position] == "CameraX" -> navigateCameraXActivity(context)
                    items[position] == "MediaStore" -> navigateMediaStoreActivity(context)
                    items[position] == "StorageTest" -> navigateStorageTestActivity(context)
                    items[position] == "DownloadTest" -> navigateDownloadTestActivity(context)
                    items[position] == "ThreadTest" -> navigateThreadTestActivity(context)
                    items[position] == "ThreadTest" -> navigateThreadTestActivity(context)
                    items[position] == "ThreadTest" -> navigateThreadTestActivity(context)
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

        private fun navigateStartModelActivity(context: Context) {
            context.startActivity(Intent(context, AActivity::class.java))
        }

        private fun navigatePersistenceActivity(context: Context) {
            context.startActivity(Intent(context, PersistenceSampleActivity::class.java))
        }

        private fun navigateFlowSampleActivity(context: Context) {
            context.startActivity(Intent(context, FlowSampleActivity::class.java))
        }

        private fun navigateQuestionActivity(context: Context, q: String = "q1", onlyYes: Boolean = false, onlyPanduan: Boolean = false) {
            val intent = Intent(context, FlowSampleActivity::class.java)
            intent.putExtra("content", q)
            intent.putExtra("onlyYes", onlyYes)
            intent.putExtra("onlyPanduan", onlyPanduan)
            context.startActivity(intent)
        }

        private fun navigateContentProviderSampleActivity(context: Context) {
            context.startActivity(Intent(context, ContentProviderSampleActivity::class.java))
        }

        private fun navigateCameraXActivity(context: Context) {
            context.startActivity(Intent(context, CameraXSampleActivity::class.java))
        }

        private fun navigateMediaStoreActivity(context: Context) {
            context.startActivity(Intent(context, MediaStoreFarseerActivity::class.java))
        }

        private fun navigateStorageTestActivity(context: Context) {
            context.startActivity(Intent(context, StorageTestActivity::class.java))
        }

        private fun navigateDownloadTestActivity(context: Context) {
            context.startActivity(Intent(context, FileDownloadActivity::class.java))
        }
        private fun navigateThreadTestActivity(context: Context) {
            context.startActivity(Intent(context, ThreadTestActivity::class.java))
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }

}
