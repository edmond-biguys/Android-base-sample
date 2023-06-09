package com.cym.sample.camera.preview

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.xmcc.androidbasesample.databinding.FragmentCameraPreviewBinding
import kotlin.math.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "CameraPreviewFragment"

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class CameraPreviewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentCameraPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCameraPreviewBinding.inflate(layoutInflater, container, false)
        initPreview()
        return binding.root
    }

    private lateinit var cameraProviderFuture : ListenableFuture<ProcessCameraProvider>

    private fun initPreview() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(requireActivity()))
    }

    private fun bindPreview(cameraProvider: ProcessCameraProvider) {

        val screenAspectRatio = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val metrics = requireActivity().windowManager.currentWindowMetrics.bounds
            aspectRatio(metrics.width(), metrics.height())
        } else {
            val displayMetrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            aspectRatio(displayMetrics.widthPixels, displayMetrics.heightPixels)
        }

        val preview = Preview.Builder()
            .setTargetAspectRatio(screenAspectRatio)
            .build()
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        //TextureViewImpl          D  Surface set on Preview.
//        binding.previewView.implementationMode = PreviewView.ImplementationMode.COMPATIBLE


        //SurfaceViewImpl          D  Surface set on Preview.
        //这个是默认模式
        with(binding) {
            previewView.implementationMode = PreviewView.ImplementationMode.PERFORMANCE
            previewView.scaleType = PreviewView.ScaleType.FIT_CENTER

        }
//        binding.previewView.scaleX = 2.2F
//        binding.previewView.scaleY = 2.2F
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
        //这里preview会返回一个camera对象，可以获取camera的一些信息，也可以对camera进行一些控制
        val camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)

    }

    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }

    companion object {

        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
    }
}