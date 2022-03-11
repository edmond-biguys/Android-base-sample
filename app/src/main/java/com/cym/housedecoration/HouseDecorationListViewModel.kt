package com.cym.housedecoration

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cym.housedecoration.bean.DecorativeMaterial

class HouseDecorationListViewModel: ViewModel() {

    val TAB_DATAS = arrayListOf("装修材料", "楼层")

    val decorativeMaterialListLiveData = MutableLiveData<List<DecorativeMaterial>>()

    fun getDecorativeMaterialList() {
        decorativeMaterialListLiveData.value = DecorativeMaterial.mockData()
    }


}