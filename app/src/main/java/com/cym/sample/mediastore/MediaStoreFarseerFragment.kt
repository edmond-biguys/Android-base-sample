package com.cym.sample.mediastore

import android.annotation.SuppressLint
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.FragmentMediaStoreFarseerBinding

/*
功能描述
1. 打开界面时，显示最近拍摄的图片和视频；第一项为拍摄按钮。
2. 使用cameraX，拍照、拍摄视频，保存到本地；短按为拍照，长按是拍摄视频。
3. 显示界面时，显示图片使用缩略图，视频使用第一帧缩略图
4. 点击图片或视频，进入预览界面，预览界面可以删除图片或视频
 */
private const val TAG = "MediaStoreFarseer"
class MediaStoreFarseerFragment : Fragment() {

    companion object {
        fun newInstance() = MediaStoreFarseerFragment()
    }

    private lateinit var binding: FragmentMediaStoreFarseerBinding
    private val viewModel by viewModels<MediaStoreFarseerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this)[MediaStoreFarseerViewModel::class.java]
        viewModel.getMediaStoreData()
        Log.i(TAG, "onCreate: ")

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMediaStoreFarseerBinding.inflate(inflater, container, false)
        Log.i(TAG, "onCreateView: ")
        initListener()
        initGrid()
        initObserver()
        return binding.root
    }

    private lateinit var adapter: MediaStoreAdapter
    private fun initGrid() {
        binding.recyclerViewMediaList.layoutManager = GridLayoutManager(context, 4).apply {
            orientation = GridLayoutManager.VERTICAL
        }
        adapter = MediaStoreAdapter(listOf())
        binding.recyclerViewMediaList.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        viewModel.mediaStoreLiveData.observe(requireActivity()) {
            Log.i(TAG, "initObserver: $it")
            binding.recyclerViewMediaList.adapter = MediaStoreAdapter(it)
            adapter.notifyDataSetChanged()
        }
        viewModel.imageMediaStoreLiveData.observe(requireActivity()) {
            Log.i(TAG, "initObserver2: $it")
            binding.recyclerViewMediaList.adapter = MediaStoreAdapter(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun initListener() {
        binding.camera.setOnClickListener {
            Log.i(TAG, "initListener: btn Camera")
            Navigation.findNavController(it).navigate(R.id.action_homeMediaStoreFragment_to_mediaStoreCameraFragment)
        }
        binding.detail.setOnClickListener {
            Log.i(TAG, "initListener: btn Detail")
            Navigation.findNavController(it).navigate(R.id.action_homeMediaStoreFragment_to_mediaStoreCameraFragment)
        }
    }

}