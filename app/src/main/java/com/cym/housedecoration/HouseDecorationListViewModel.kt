package com.cym.housedecoration

import androidx.lifecycle.ViewModel
import com.cym.housedecoration.bean.DecorativeMaterial

class HouseDecorationListViewModel: ViewModel() {

    val TAB_DATAS = arrayListOf("装修材料", "楼层")

    fun getDecorativeMaterialList(): List<DecorativeMaterial> {

        return DecorativeMaterial.mockData()
    }

}