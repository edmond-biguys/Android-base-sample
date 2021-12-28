package com.example.jetpack.livedata.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.jetpack.databinding.LoginFragmentBinding


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    private lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    /*
    1. victory
2.battle without honor
3.pacific rim
4.she is my sin
5.he is a pirate

1、liberators
2、Requiem For A Dream-钢琴曲
3、Flight Of The Silverbird
4、Last Reunion
5、Shadowfall
6、Becoming a Legend
7、Archangel
8、Mind Heist
9、Brotherhood
10、SkyWorld
     */

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(LoginViewModel::class.java)
        viewModel.liveData.observe(this, Observer {
            //println("observe in fragment login result is $it")
        })

        binding.buttonLogin.setOnClickListener {
            viewModel.login(binding.editTextTextPersonName.text.toString(), binding.editTextNumberPassword.text.toString())
        }
    }

}