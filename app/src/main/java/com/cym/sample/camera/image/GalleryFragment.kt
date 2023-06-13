package com.cym.sample.camera.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xmcc.androidbasesample.databinding.FragmentGallery2Binding


/**
 * create an instance of this fragment.
 */
class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGallery2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGallery2Binding.inflate(inflater, container, false)
        return binding.root
    }

}