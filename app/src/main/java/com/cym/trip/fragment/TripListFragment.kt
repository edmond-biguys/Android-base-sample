package com.cym.trip.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xmcc.androidbasesample.R

class TripListFragment : Fragment() {

    companion object {
        fun newInstance() = TripListFragment()
    }

    private lateinit var viewModel: TripListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trip_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TripListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}