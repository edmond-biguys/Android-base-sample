package com.cym.housedecoration.placeholder

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.cym.housedecoration.HouseDecorationListViewModel
import com.cym.housedecoration.detail.DecorativeMaterialDetailViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class PlaceholderContentTest {
    @Test
    fun add() {
        println("add ${1+2}" )
    }

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testGetTestData() {
        println(",,,,")
//        val viewModel = DecorativeMaterialDetailViewModel(context as Application)
//        println(viewModel.getTestData())
    }


}