package com.cym.sample.camera.image

import android.content.ContentValues
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.FragmentImageCaptureBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class ImageCaptureFragment : Fragment() {

    private val TAG = "ImageCaptureFragment"

    private lateinit var imageCapture: ImageCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutorService = Executors.newSingleThreadExecutor()
    }
    private lateinit var binding: FragmentImageCaptureBinding
    private lateinit var cameraExecutorService: ExecutorService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentImageCaptureBinding.inflate(inflater, container, false)
        buttonListener()
        bindCameraUseCase()
        return binding.root
    }

    private fun bindCameraUseCase() {
        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

    }

    private fun buttonListener() {
        binding.buttonImageCapture.setOnClickListener {

            if (!this::imageCapture.isInitialized) return@setOnClickListener

            val name = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.CHINA).format(System.currentTimeMillis())

            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                val appName = requireContext().resources.getString(R.string.app_name)
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$appName")
            }

            val outputOptions = ImageCapture.OutputFileOptions
                .Builder(requireContext().contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                .build()

            imageCapture.takePicture(outputOptions, cameraExecutorService,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.i(TAG, "onError: $exception")
                    }

                })
        }
    }




}