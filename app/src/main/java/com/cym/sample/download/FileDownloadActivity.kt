package com.cym.sample.download

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadSampleListener
import com.liulishuo.filedownloader.FileDownloader
import com.xmcc.androidbasesample.databinding.ActivityDownloadTestBinding
import java.text.DecimalFormat
import kotlin.concurrent.thread

/**
 * Created by caoj on 2023/12/20.
 */
private const val TAG = "FileDownloadActivity"
class FileDownloadActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDownloadTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadTestBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        FileDownloader.setup(applicationContext)
        download()
    }

    private fun download() {
        Log.i(TAG, "download: start")
        thread {
            Log.i(TAG, "download: thread start")
            val url = ""
            val path = "/mnt/sdcard"
            val isDir = true
            val task = FileDownloader.getImpl().create(url)
                .setPath(path, isDir)
                .setListener(object : FileDownloadSampleListener() {

                    override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                        super.pending(task, soFarBytes, totalBytes)
                        Log.i(TAG, "pending: ")
                    }

                    override fun completed(task: BaseDownloadTask?) {
                        super.completed(task)
                        val content = "${task?.path} ${task?.filename} ${task?.targetFilePath} ${task?.speed}"
                        Log.i(TAG, "completed: $content")
                    }

                    override fun error(task: BaseDownloadTask?, e: Throwable?) {
                        super.error(task, e)
                        Log.i(TAG, "error: $e")
                    }

                    override fun progress(
                        task: BaseDownloadTask?,
                        soFarBytes: Int,
                        totalBytes: Int
                    ) {
                        super.progress(task, soFarBytes, totalBytes)
                        val percent = DecimalFormat("0.00").format(soFarBytes.toFloat() / totalBytes.toFloat() * 100)
                        Log.i(TAG, "progress: $soFarBytes $totalBytes $percent%")
                    }

                })
            task.start()
        }

    }

}