package com.cym.sample.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.FragmentCameraSampleListBinding

/**
 * A fragment representing a list of Items.
 */
class CameraSampleFragment : Fragment() {

    private lateinit var binding: FragmentCameraSampleListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val values = listOf("Preview", "ImageCapture", "ImageAnalysis", "VideoCapture")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraSampleListBinding.inflate(inflater, container, false)
        val view = binding.root
        view.layoutManager = LinearLayoutManager(context)

        view.adapter = MyItemRecyclerViewAdapter(values) {
            when (it) {
                values[0] -> findNavController().navigate(R.id.sample_to_preview)
                values[1] -> findNavController().navigate(R.id.sample_to_image_capture)
                values[2] -> findNavController().navigate(R.id.sample_to_preview)
                values[3] -> findNavController().navigate(R.id.sample_to_preview)
                else -> {
                    //do nothing
                }
            }
        }

        return view
    }

//    private fun navigate
}