package com.example.jetpack.livedata.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val liveData = MutableLiveData<String>()

    fun login(name: String, password: String) {
        println("name $name password $password")
        GlobalScope.launch {
            delay(2000)
            try {
                if (name == "abc" && password == "123") {
                    println("success ${Thread.currentThread()}")
                    liveData.postValue("login success")
                } else {
                    liveData.postValue("login failed")
                    println("failed ${Thread.currentThread()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}