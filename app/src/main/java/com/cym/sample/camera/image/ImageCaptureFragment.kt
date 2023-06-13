package com.cym.sample.camera.image

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraState
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.common.util.concurrent.ListenableFuture
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

    private lateinit var preview: Preview
    private lateinit var imageCapture: ImageCapture
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutorService = Executors.newSingleThreadExecutor()
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity())
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
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            bindCameraUseCase(cameraProvider)
        }, ContextCompat.getMainExecutor(requireActivity()))

        return binding.root
    }

    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private fun bindCameraUseCase(cameraProvider: ProcessCameraProvider) {
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()

        preview = Preview.Builder()
            .build()
        binding.previewView.scaleType = PreviewView.ScaleType.FIT_CENTER
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)


        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
            .build()

        //在bind前，先unbind。比如切换摄像头等。
        cameraProvider.unbindAll()

        val camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
        observeCamera(camera)
    }

    private fun observeCamera(camera: Camera) {
        camera.cameraInfo.cameraState.observe(viewLifecycleOwner) {cameraState ->
            when(cameraState.type) {
                CameraState.Type.PENDING_OPEN -> {
                    Log.i(TAG, "observeCamera: pending-open")
                }
                CameraState.Type.OPENING -> {
                    Log.i(TAG, "observeCamera: opening")
                }
                CameraState.Type.OPEN -> {
                    Log.i(TAG, "observeCamera: open")
                }
                CameraState.Type.CLOSING -> {
                    Log.i(TAG, "observeCamera: closing")
                }
                CameraState.Type.CLOSED -> {
                    Log.i(TAG, "observeCamera: closed")
                }
            }

            cameraState.error?.let { error ->
                when(error.code) {
                    CameraState.ERROR_STREAM_CONFIG ->{
                        Log.i(TAG, "observeCamera: ERROR_STREAM_CONFIG")
                    }
                    CameraState.ERROR_CAMERA_DISABLED ->{
                        Log.i(TAG, "observeCamera: ERROR_CAMERA_DISABLED")
                    }
                    CameraState.ERROR_MAX_CAMERAS_IN_USE ->{
                        Log.i(TAG, "observeCamera: ERROR_MAX_CAMERAS_IN_USE")
                    }
                    CameraState.ERROR_CAMERA_IN_USE ->{
                        Log.i(TAG, "observeCamera: ERROR_CAMERA_IN_USE")
                    }
                    CameraState.ERROR_CAMERA_FATAL_ERROR ->{
                        Log.i(TAG, "observeCamera: ERROR_CAMERA_FATAL_ERROR")
                    }
                    CameraState.ERROR_DO_NOT_DISTURB_MODE_ENABLED ->{
                        Log.i(TAG, "observeCamera: ERROR_DO_NOT_DISTURB_MODE_ENABLED")
                    }
                    CameraState.ERROR_OTHER_RECOVERABLE_ERROR ->{
                        Log.i(TAG, "observeCamera: ERROR_OTHER_RECOVERABLE_ERROR")
                    }

                }
            }
        }


    }

    private fun buttonListener() {
        //拍照
        binding.buttonImageCapture.setOnClickListener {
            if (!this::imageCapture.isInitialized) return@setOnClickListener
            capture()
        }
        //跳转到相册
        binding.buttonThumbnail.setOnClickListener {
            //val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //startActivity(intent)

            findNavController().navigate(R.id.camera_image_capture_to_gallery)
        }
        //切换摄像头
        binding.buttonCameraSwitch.setOnClickListener {
            lensFacing = if (CameraSelector.LENS_FACING_BACK == lensFacing)
                CameraSelector.LENS_FACING_FRONT
            else
                CameraSelector.LENS_FACING_BACK

            bindCameraUseCase(cameraProviderFuture.get())
        }
    }

    private fun capture() {

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

        //实际上拍照，不用预览也是可以拍的，只不过没有预览，你不知道拍的是什么内容
        imageCapture.takePicture(outputOptions, cameraExecutorService,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = output.savedUri
                    Log.i(TAG, "onImageSaved: $savedUri")

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        //设置缩略图
                        setGalleryThumbnail(savedUri.toString())
                    }

                    // Implicit broadcasts will be ignored for devices running API level >= 24
                    // so if you only target API level 24+ you can remove this statement
                    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                        // Suppress deprecated Camera usage needed for API level 23 and below
                        @Suppress("DEPRECATION")
                        requireActivity().sendBroadcast(
                            Intent(android.hardware.Camera.ACTION_NEW_PICTURE, savedUri)
                        )
                    }


                }

                override fun onError(exception: ImageCaptureException) {
                    Log.i(TAG, "onError: $exception")
                }

            })
    }

    //拍照完成后，在跳转到相册按钮上，显示上一张照片的缩略图。
    private fun setGalleryThumbnail(fileName: String) {
        with(binding) {
            buttonThumbnail.post {
                buttonThumbnail.setPadding(resources.getDimension(R.dimen.stroke_small).toInt())

                Glide.with(buttonThumbnail)
                    .load(fileName)
                    .apply(RequestOptions.circleCropTransform())
                    .into(buttonThumbnail)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutorService.shutdown()

    }



}