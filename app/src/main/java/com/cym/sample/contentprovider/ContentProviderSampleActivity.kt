package com.cym.sample.contentprovider

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.CursorLoader
import com.cym.sample.room.entity.Course
import com.cym.utilities.logi
import com.google.gson.Gson
import com.xmcc.androidbasesample.databinding.ActivityContentProviderSampleBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by caoj on 2022/10/19.
 */
class ContentProviderSampleActivity: AppCompatActivity() {

    private lateinit var binding: ActivityContentProviderSampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentProviderSampleBinding.inflate(layoutInflater).also { setContentView(it.root) }
        logi(CourseProviders.COURSE_URI)
        GlobalScope.launch {
            val cursor = contentResolver.query(CourseProviders.COURSE_URI, null, null, null)
            cursor?.let {
                while (it.moveToNext()) {
                    val course = Course()
                    course.id = it.getInt(it.getColumnIndex(CourseProviders.CourseColumn.ID))
                    course.name = it.getString(it.getColumnIndex(CourseProviders.CourseColumn.NAME))
                    course.teacher_name = it.getString(it.getColumnIndex(CourseProviders.CourseColumn.TEACHER_NAME))
                    course.watch_count = it.getInt(it.getColumnIndex(CourseProviders.CourseColumn.WATCH_COUNT))
                    course.video_url = it.getString(it.getColumnIndex(CourseProviders.CourseColumn.VIDEO_URL))
                    logi("course ${Gson().toJson(course)}")
                }
            }
            logi("cursor $cursor")
        }

        binding.button7.setOnClickListener {
            GlobalScope.launch {
                val v = ContentValues()
                v.put(CourseProviders.CourseColumn.ID, 1)
                v.put(CourseProviders.CourseColumn.NAME, "name1")
                v.put(CourseProviders.CourseColumn.TEACHER_NAME, "teacher-name1")
                v.put(CourseProviders.CourseColumn.WATCH_COUNT, 10)
                v.put(CourseProviders.CourseColumn.VIDEO_URL, "video-url1")
                contentResolver.insert(CourseProviders.COURSE_URI, v)
            }
        }

    }
}