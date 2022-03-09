package com.cym.housedecoration.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cym.housedecoration.bean.DecorativeMaterial
import java.util.*

class DecorativeMaterialDetailViewModel(application: Application): AndroidViewModel(application) {

    val decorativeMaterialdetailLiveData = MutableLiveData<DecorativeMaterial>()

    fun requestDetail() {
        val list = DecorativeMaterial.mockData()
        decorativeMaterialdetailLiveData.value = list[Random().nextInt(list.size - 1)]
    }

    fun getTestData(): String {
        return "test01"
    }



}