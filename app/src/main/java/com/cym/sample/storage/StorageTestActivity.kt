package com.cym.sample.storage

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xmcc.androidbasesample.databinding.ActivityStorageTestBinding

/**
 * Created by caoj on 2023/12/7.
 */
class StorageTestActivity: AppCompatActivity() {
    private companion object {
        const val TAG = "StorageTestActivity"
        const val FILE_NAME = "appSpecificFileTest.txt"
    }
    private lateinit var binding: ActivityStorageTestBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.i(TAG, "onCreate: 1")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
        binding = ActivityStorageTestBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        initClickListener()
        showFileName()
    }


    private fun initClickListener() {
        binding.btnWrite.setOnClickListener {
            write()
        }
        binding.btnRead.setOnClickListener {
            read()
        }
    }
    private var index = 1

    private fun read() {
        val fis = openFileInput(FILE_NAME)

        try {
            val byteArray = ByteArray(fis.available())
            fis.read(byteArray)
            val content = String(byteArray)
            binding.tvName.text = content
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fis.close()
        }
    }
    private fun write() {
        val fos = openFileOutput(FILE_NAME, Context.MODE_APPEND)
        try {
            val content = "通过app specific files 记录，第${index}次\n"
            fos.write(content.toByteArray())
            index++
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fos.close()
        }
    }

    private fun showFileName() {
        // /data/user/0/com.xmcc.androidbasesample/files
        // /data/user/0/com.xmcc.androidbasesample/cache
        Log.i(TAG, "showFileName: fileDir $filesDir")
        Log.i(TAG, "showFileName: cacheDir $cacheDir")

        // /storage/emulated/0/Android/data/com.xmcc.androidbasesample/files
        // /storage/emulated/0/Android/data/com.xmcc.androidbasesample/cache
        Log.i(TAG, "showFileName: cacheDir ${getExternalFilesDir("")}")
        Log.i(TAG, "showFileName: cacheDir $externalCacheDir")
    }
}