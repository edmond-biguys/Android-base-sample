package com.cym.housedecoration.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cym.housedecoration.bean.DecorativeMaterial
import java.math.BigDecimal
import java.util.*

class DecorativeMaterialDetailViewModel(application: Application): AndroidViewModel(application) {

    val decorativeMaterialdetailLiveData = MutableLiveData<DecorativeMaterial>()

    fun requestDetail() {
        val list = DecorativeMaterial.mockData()
        decorativeMaterialdetailLiveData.value = list[Random().nextInt(list.size - 1)]
    }


    fun saveMaterialTitle(title: String) {

    }

    fun saveMaterialDesc(title: String) {

    }

    fun saveMaterialTotalPrice(price: BigDecimal) {

    }

    fun saveMaterialInfo(info: DecorativeMaterial) {

    }

    fun getTestData(): String {
        return "test01"
    }



}