package com.xmcc.androidbasesample.fragment.navigation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xmcc.androidbasesample.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        binding.textDashboard.text = ""
        dashboardViewModel.text.observe(viewLifecycleOwner, {
            binding.textDashboard.text = it
        })
        return binding.root
    }
}