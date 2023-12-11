package com.cym.sample.storage

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.os.storage.StorageManager
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
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
        binding.btnShowPath.setOnClickListener {
            val root = Environment.getRootDirectory()
            val p1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Environment.getStorageDirectory()
            } else {
                null
            }
            val p2 = Environment.getExternalStorageDirectory()
            val p3 = Environment.getDownloadCacheDirectory()
            Log.i(TAG, "initClickListener: root $root p1 $p1 p2 $p2 p3 $p3")
            Environment.DIRECTORY_DOWNLOADS

            // 下载
            val downloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            // 闹铃
            val alarms = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS)
            // 有声书
            val audioBooks = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_AUDIOBOOKS)
            } else {
                null
            }
            // 相机目录 digital camera in memory
            val dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            // 文档
            val documents = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            val movies = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
            val music = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
            // 通知
            val notifications = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS)

            val pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            //播客
            val podcasts = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS)
            //录音
            val recordings = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RECORDINGS)
            } else {
                null
            }
            //铃声
            val ringtones = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES)

            Log.i(TAG, "initClickListener: downloads $downloads")
            Log.i(TAG, "initClickListener: alarms $alarms audioBooks $audioBooks dcim $dcim documents $documents movies $movies music $music notifications $notifications pictures $pictures podcasts $podcasts recordings $recordings ringtones $ringtones")
            writeFile(downloads.absolutePath, "test.txt", "test")


        }

        binding.btnPickMedia.setOnClickListener {
            startPickMedia()
        }

        binding.btnPickMultiMedia.setOnClickListener {
            startPickMultiMedia()
        }

    }
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri->
        Log.i(TAG, "startPickMedia: $uri")
    }
    private val pickMultiMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { list ->
        Log.i(TAG, "pickMultiMedia: $list")
    }
    private fun startPickMedia() {

//        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }

    private fun startPickMultiMedia() {
        pickMultiMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
    }

    private var index = 1

    private fun read() {
        if (!File("${filesDir.absolutePath}/$FILE_NAME").exists()) {
            Log.i(TAG, "read: file not exists")
            return
        }
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
        val path = "$cacheDir/test"
        val fileName = FILE_NAME_CACHE
        binding.tvName.text = readFile(path, fileName)

        val fileCache = File("$path/$fileName")
        val storageManager = getSystemService(Context.STORAGE_SERVICE) as StorageManager
        val uuid = storageManager.getUuidForPath(fileCache)
        val bytes = storageManager.getCacheQuotaBytes(uuid)
        Log.i(TAG, "readCache: cache $bytes uuid $uuid")
    }
    //写入internal的cache
    private fun writeCache() {
        val path = "$cacheDir/test"
        val fileName = FILE_NAME_CACHE
        val content = "通过app specific cache files 记录，第${index}次\n"
        writeFile(path, fileName, content)
        index++
    }
    //读取external的cache
    private fun readExternalCache() {
        val path = "$externalCacheDir/test"
        val fileName = FILE_NAME_CACHE
        binding.tvName.text = readFile(path, fileName)
    }

    //写入external的cache
    private fun writeExternalCache() {
        val path = "$externalCacheDir/test"
        val fileName = FILE_NAME_CACHE
        val content = "通过app specific external cache files 记录，第${index}次\n"
        writeFile(path, fileName, content)
        index++
    }

    private fun readFile(path: String, fileName: String): String {
        var fis: FileInputStream? = null
        try {
            if (!isExternalStorageReadable()) {
                Log.i(TAG, "readFile: storage not readable")
                return ""
            }

            val pathFile = File(path)
            val file = File("$path/$fileName")
            if (!pathFile.exists())
            {
                Log.i(TAG, "readFile: path not exists")
                return ""
            }
            if (!file.exists()) {
                Log.i(TAG, "readFile: file not exists")
                return ""
            }
            fis = FileInputStream(file)
            val byteArray = ByteArray(fis.available())
            fis.read(byteArray)
            return String(byteArray)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            fis?.close()
        }
        return ""
    }

    private fun writeFile(path: String, fileName: String, content: String) {
        var fos: FileOutputStream? = null
        try {
            val file = File("$path/$fileName")
            val pathFile = File("$path")
            if (!pathFile.exists())
                pathFile.mkdirs()
            if (!file.exists()) {
                file.createNewFile()
            }
            fos = FileOutputStream(file, true)
            fos.write(content.toByteArray())
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