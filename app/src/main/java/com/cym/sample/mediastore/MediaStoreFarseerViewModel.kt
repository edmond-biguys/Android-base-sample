package com.cym.sample.mediastore

import android.app.Application
import android.content.ContentResolver
import android.content.ContentUris
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.cym.utilities.logi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

private const val TAG = "MediaStoreFarseerVM"
class MediaStoreFarseerViewModel(application: Application) : AndroidViewModel(application) {
    fun getMediaStoreData() {
        logi("getMediaStoreData")
    }

    val mediaStoreLiveData = liveData<List<MediaStoreItem>> {

        val collection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Video.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
                )
            } else {
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            }
        val projection = arrayOf(MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE)
        val selection = "${MediaStore.Video.Media.DURATION} >= ?"
        val selectionArgs = arrayOf(
            TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS).toString()
        )
        val sortOrder = "${MediaStore.Video.Media.DISPLAY_NAME} ASC"
        application.contentResolver.query(
            collection,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            //cache column indices.
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val list = mutableListOf<MediaStoreItem>()
            while (cursor.moveToNext()) {
                //get values of columns for a given video.
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val duration = cursor.getInt(durationColumn)
                val size = cursor.getInt(sizeColumn)
                val contentUri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
                val item = MediaStoreItem(
                    contentUri,
                    null,
                    id,
                    name,
                    duration,
                    size
                )
                list.add(item)
            }
            Log.i(TAG, ": $list")
            emit(list)

        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    val imageMediaStoreLiveData = MutableLiveData<List<MediaStoreItem>>()

    private var pageIndex = 0
    private val pageSize = 39
    @RequiresApi(Build.VERSION_CODES.Q)
    fun updateImages(init: Boolean = false) {
        viewModelScope.launch {
            val cr = getApplication<Application>().contentResolver

            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.DATE_ADDED,

                )

            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
            val selection = "${MediaStore.Images.Media.DATE_ADDED} >= ?"
            val selectionArgs = arrayOf(
                dateToTimestamp(15, 11, 2023).toString()
            )

            val limit = "$pageSize offset ${pageSize*pageIndex}"
            val queryArgs = createSqlQueryBundle(selection, selectionArgs, sortOrder, limit)
//        val cursor = cr.query(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            projection,
//            selection,
//            selectionArgs,
//            sortOrder
//        )
            val cursor = cr.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                queryArgs,
                null)
            cursor?.let {
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val path = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                val list = mutableListOf<MediaStoreItem>()
                list.add(MediaStoreItem(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null,
                    0,
                    "拍照",
                    0,
                    0
                ))

                while (it.moveToNext()) {
                    try {
                        val id = cursor.getLong(idColumn)
                        val name = cursor.getString(nameColumn)
                        val size = cursor.getInt(sizeColumn)
                        val contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                        val item = MediaStoreItem(
                            contentUri,
                            cr.loadThumbnail(contentUri, Size(200, 200), null),
//                        null,
                            id,
                            name,
                            0,
                            size
                        )
                        list.add(item)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                Log.i(TAG, "list.size ${list.size} $list: ")

                //emit(list)
                if (list.size > 1) {
                    imageMediaStoreLiveData.postValue(list)
                    pageIndex++
                } else if (init) {
                    imageMediaStoreLiveData.postValue(list)
                }

            }
        }
    }

    fun createSqlQueryBundle(
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?,
        limit: String?
    ): Bundle? {
        if (selection == null && selectionArgs == null && sortOrder == null && limit == null) {
            return null
        }
        val queryArgs = Bundle()
        if (selection != null) {
            queryArgs.putString(ContentResolver.QUERY_ARG_SQL_SELECTION, selection)
        }
        if (selectionArgs != null) {
            queryArgs.putStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS, selectionArgs)
        }
        if (sortOrder != null) {
            queryArgs.putString(ContentResolver.QUERY_ARG_SQL_SORT_ORDER, sortOrder)
        }
        if (limit != null) {
            queryArgs.putString(ContentResolver.QUERY_ARG_SQL_LIMIT, limit)
        }
        return queryArgs
    }

    private fun dateToTimestamp(day: Int, month: Int, year: Int): Long =
        SimpleDateFormat("dd.MM.yyyy").let { formatter ->
            TimeUnit.MICROSECONDS.toSeconds(formatter.parse("$day.$month.$year")?.time ?: 0)
        }

}