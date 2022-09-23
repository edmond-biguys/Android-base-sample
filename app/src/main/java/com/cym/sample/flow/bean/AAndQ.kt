package com.cym.sample.flow.bean

/**
 * Created by caoj on 2022/9/22.
 */
data class AAndQ(
    val title: String,
    val items: MutableList<String>,
    val answer: String,
    val repeatTimes: Int = 0,
)
