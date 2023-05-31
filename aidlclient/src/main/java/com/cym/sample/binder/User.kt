package com.cym.sample.binder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by caoj on 2023/1/12.
 */
@Parcelize
data class User(
    val name: String = "tata",
    val age: Int = 12
): Parcelable