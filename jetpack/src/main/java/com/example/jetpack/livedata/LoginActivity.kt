package com.example.jetpack.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack.R
import com.example.jetpack.databinding.LoginActivityBinding
import com.example.jetpack.livedata.ui.main.LoginFragment
import com.example.jetpack.livedata.ui.main.LoginViewModel


class LoginActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.login_activity)

        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }

        binding.container.postDelayed({
            loginViewModel.liveData.observeForever {
                println("observe login in activity result $it")
            }
        }, 1000)

    }
}