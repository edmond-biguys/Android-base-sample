package com.cym.sample.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by caoj on 2022/10/25.
 */
@Entity(tableName = "course")
data class Course(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var teacher_name: String = "",
    var watch_count: Int = 0,
    var video_url: String = ""
)
