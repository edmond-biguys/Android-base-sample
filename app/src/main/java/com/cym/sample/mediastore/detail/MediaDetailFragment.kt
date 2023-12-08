package com.cym.sample.mediastore.detail

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.FragmentMediaDetailBinding

private const val MEDIA_URI = "mediaUri"
private const val TAG = "MediaDetailFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [MediaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MediaDetailFragment : Fragment() {

    private var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                uri = it.getParcelable(MEDIA_URI, Uri::class.java)
                Log.i(TAG, "onCreate: $uri")
            }
        }
    }
    private lateinit var binding: FragmentMediaDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgMedia.setImageURI(uri)
    }
}