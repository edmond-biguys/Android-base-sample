package com.cym.sample.threadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ActivityThreadTestBinding

private const val TAG = "ThreadTest"
class ThreadTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThreadTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadTestBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        initListener()
    }

    private fun initListener() {
        binding.btnNew.setOnClickListener {
            newThread()
        }
        binding.btnJoin.setOnClickListener {
            joinTest()
        }
    }

    //new Thread
    private fun newThread() {
        val thread  = Thread {
            Log.i(TAG, "newThread: ")
        }
        Log.i(TAG, "newThread: $thread")
        Log.i(TAG, "newThread: new state ${thread.state}")
        thread.start()
        Log.i(TAG, "newThread: start state ${thread.state}")
    }

    //test join
    private fun joinTest() {
        val str: Any = "abc"
        //创建thread A
        val threadA = Thread {
            Log.i(TAG, "joinTest: A running")

            //创建thread B，并启动
            val threadB = Thread {
                Log.i(TAG, "joinTest: B running")
                Thread.sleep(5000)
                Log.i(TAG, "joinTest: B end")
            }
            threadB.start()
            Log.i(TAG, "joinTest: B start")


            //创建thread C，并启动
            val threadC = Thread {
                Log.i(TAG, "joinTest: C running")
                Thread.sleep(3000)
                Log.i(TAG, "joinTest: C end")
            }
            threadC.start()
            Log.i(TAG, "joinTest: C start")

            Thread {
                Thread.sleep(800)
                Log.i(TAG, "joinTest: B state ${threadB.state}")
                Log.i(TAG, "joinTest: C state ${threadC.state}")
                Thread.sleep(2400)
                Log.i(TAG, "joinTest2: B state ${threadB.state}")
                Log.i(TAG, "joinTest2: C state ${threadC.state}")

            }.start()

            threadB.join(1500)
            Log.i(TAG, "joinTest: B after join")
            threadC.join()
            Log.i(TAG, "joinTest: C after join")


        }
        Log.i(TAG, "joinTest: A start")
        threadA.start()

    }


}