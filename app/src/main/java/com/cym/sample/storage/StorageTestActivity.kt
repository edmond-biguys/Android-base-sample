package com.cym.sample.storage

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.xmcc.androidbasesample.databinding.ActivityStorageTestBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Created by caoj on 2023/12/7.
 */
class StorageTestActivity: AppCompatActivity() {
    private companion object {
        const val TAG = "StorageTestActivity"
        const val FILE_NAME = "appSpecificFileTest.txt"
        const val FILE_NAME_CACHE = "appSpecificFileTestCache.txt"
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
        binding.btnReadCache.setOnClickListener {
            readCache()
        }
        binding.btnWriteCache.setOnClickListener {
            writeCache()
        }
        binding.btnExternalReadCache.setOnClickListener {
            readExternalCache()
        }
        binding.btnExternalWriteCache.setOnClickListener {
            writeExternalCache()
        }
        binding.btnClearCache.setOnClickListener {
            clearCacheAndFiles()
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

    //读取internal的cache
    private fun readCache() {
        var fis: FileInputStream? = null
        try {
            val fileCache = File("$cacheDir/test/$FILE_NAME_CACHE")
            val testCacheFile = File("$cacheDir/test")
            if (!testCacheFile.exists())
                testCacheFile.mkdirs()
            if (!fileCache.exists()) {
                fileCache.createNewFile()
            }
            fis = FileInputStream(fileCache)
            val byteArray = ByteArray(fis.available())
            fis.read(byteArray)
            val content = String(byteArray)
            binding.tvName.text = content

            fileCache.readLines().forEach {
                Log.i(TAG, "readCache: $it")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fis?.close()
        }
    }
    //写入internal的cache
    private fun writeCache() {
        var fos: FileOutputStream? = null
        try {
            val fileCache = File("$cacheDir/test/$FILE_NAME_CACHE")
            val testCacheFile = File("$cacheDir/test")
            if (!testCacheFile.exists())
                testCacheFile.mkdirs()
            if (!fileCache.exists()) {
                fileCache.createNewFile()
            }
            fos = FileOutputStream(fileCache, true)
            fos.write("通过app specific cache files 记录，第${index}次\n".toByteArray())
            index++
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fos?.close()
        }
    }
    //读取external的cache
    private fun readExternalCache() {

        var fis: FileInputStream? = null
        try {
            //外部存储空间使用前，需要先判断是否可以访问
            if (!isExternalStorageReadable()) {
                return
            }

            val fileCache = File("$externalCacheDir/test/$FILE_NAME_CACHE")
            val testCacheFile = File("$externalCacheDir/test")
            if (!testCacheFile.exists())
                testCacheFile.mkdirs()
            if (!fileCache.exists()) {
                fileCache.createNewFile()
            }
            fis = FileInputStream(fileCache)
            val byteArray = ByteArray(fis.available())
            fis.read(byteArray)
            val content = String(byteArray)
            binding.tvName.text = content

            fileCache.readLines().forEach {
                Log.i(TAG, "readCache: $it")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fis?.close()
        }
    }

    //写入external的cache
    private fun writeExternalCache() {
        var fos: FileOutputStream? = null
        try {
            if (!isExternalStorageWritable()) {
                return
            }
            val fileCache = File("$externalCacheDir/test/$FILE_NAME_CACHE")
            val testCacheFile = File("$externalCacheDir/test")
            if (!testCacheFile.exists())
                testCacheFile.mkdirs()
            if (!fileCache.exists()) {
                fileCache.createNewFile()
            }
            fos = FileOutputStream(fileCache, true)
            fos.write("通过app specific external cache files 记录，第${index}次\n".toByteArray())
            index++
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fos?.close()
        }
    }

    private fun clearCacheAndFiles() {
        //清除app specific files
        deleteFile(FILE_NAME)
        //清除app specific cache files
        val fileCache = File("$cacheDir/test/$FILE_NAME_CACHE")
        fileCache.delete()
        //清除app specific external cache files
        val fileExternalCache = File("$externalCacheDir/test/$FILE_NAME_CACHE")
        fileExternalCache.delete()
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }
    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
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