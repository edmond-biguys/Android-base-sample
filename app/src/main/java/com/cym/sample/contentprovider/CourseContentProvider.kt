package com.cym.sample.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.cym.sample.room.AppDatabase
import com.cym.sample.room.dao.CourseDao
import com.cym.sample.room.entity.Course
import com.cym.utilities.logi

/**
 * Created by caoj on 2022/10/24.
 */
class CourseContentProvider: ContentProvider() {

    private lateinit var courseDao: CourseDao
    private val TAG = "CourseContentProvider"

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.xmcc.androidbasesample.provider", "course", 1)
    }

    override fun onCreate(): Boolean {
        logi("$TAG onCreate")
        courseDao = AppDatabase.getInstance(context!!).getCourseDao()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        logi("$TAG query")
        val cursor = courseDao.getAll2()
        return cursor
    }

    override fun getType(uri: Uri): String? {
        logi("$TAG getType")
        return ""
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        logi("$TAG insert")
        values?.let {
            courseDao.insertAll(
                Course(
                    name = it.getAsString(CourseProviders.CourseColumn.NAME),
                    teacher_name = it.getAsString(CourseProviders.CourseColumn.TEACHER_NAME),
                    watch_count = it.getAsInteger(CourseProviders.CourseColumn.WATCH_COUNT),
                    video_url = it.getAsString(CourseProviders.CourseColumn.VIDEO_URL),
                ))
        }
        return uri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        logi("$TAG delete")

        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        logi("$TAG update")
        return 0
    }
}

object CourseProviders {
    val AUTHORITIES = "com.xmcc.androidbasesample.provider"
    val COURSE_PATH = "course"
    val BASE_URI = Uri.parse("content://$AUTHORITIES")
    val COURSE_URI = Uri.withAppendedPath(BASE_URI, COURSE_PATH)

    object CourseColumn {
        val ID = "id"
        val NAME = "name"
        val TEACHER_NAME = "teacher_name"
        val WATCH_COUNT = "watch_count"
        val VIDEO_URL = "video_url"
    }

}