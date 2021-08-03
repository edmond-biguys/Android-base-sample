package com.xmcc.androidbasesample.fragment.navigation.nestedgraph.ordernode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

/**
 * Created by caoj on 2021/8/2.
 */
abstract class BaseFragment: Fragment() {

    protected lateinit var binding: ViewBinding
    protected abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getBinding(inflater, container)
        return binding.root
    }

    protected abstract fun initView()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }


}