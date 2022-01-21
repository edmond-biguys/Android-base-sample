package com.cym.sunflower

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cym.sunflower.viewmodels.HomeViewPagerViewModel
import com.xmcc.androidbasesample.R

class HomeViewPagerFragment : Fragment() {

    companion object {
        fun newInstance() = HomeViewPagerFragment()
    }

    private lateinit var viewModel: HomeViewPagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewPagerViewModel::class.java)
    }

}