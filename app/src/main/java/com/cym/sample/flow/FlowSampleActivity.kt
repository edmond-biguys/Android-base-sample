package com.cym.sample.flow

import android.annotation.SuppressLint
import android.content.res.Resources.getSystem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.cym.sample.flow.bean.AAndQ
import com.cym.utilities.logi
import com.xmcc.androidbasesample.dataStore
import com.xmcc.androidbasesample.databinding.ActivityFlowSampleBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class FlowSampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlowSampleBinding
    private val viewModel by viewModels<LastNewsViewModel>()

    private val list = mutableListOf<AAndQ>()
    private val keyMap = mutableMapOf<String, Int>()

    var index = 0
    var q: String = "q1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logi("onCreate")
        binding = ActivityFlowSampleBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        viewModel.start()
        initListener()

        val tempQ = intent.getStringExtra("content")
        logi("$tempQ")
        if (tempQ != null) {
            q = tempQ
            questionView()
        }
    }

    private fun questionView() {
        //q1 为判断、选择题；q2 为名词解释题
        GlobalScope.launch {
            readNowCursor()
            println("开始读取")
            val assetManager = assets
            if (q == "q1") {
                var ins = assetManager.open("gycs1_50.txt")
                var bufferedReader = BufferedReader(InputStreamReader(ins, "UTF-8"))
                readFileInfo(bufferedReader, q)
                ins = assetManager.open("gycs2_50.txt")
                bufferedReader = BufferedReader(InputStreamReader(ins, "UTF-8"))
                readFileInfo(bufferedReader, q)
            } else {
                val ins = assetManager.open("gycs3_50.txt")
                val bufferedReader = BufferedReader(InputStreamReader(ins, "UTF-8"))
                readFileInfo(bufferedReader, q)
            }


            logi("去重前数量$index")

            if (q == "q2") {
                with(binding) {
                    item1.visibility = View.GONE
                    item2.visibility = View.GONE
                    item3.visibility = View.GONE
                    item4.visibility = View.GONE
                    item5.visibility = View.GONE
                    item6.visibility = View.GONE
                }
            }
            showView()

        }
    }

    val q1CursorKey = intPreferencesKey("q1Cursor")
    val q2CursorKey = intPreferencesKey("q2Cursor")
    private fun readNowCursor() {
        GlobalScope.launch {
            try {
                val flow: Flow<Int> = dataStore.data.map { preferences ->
                    logi("readNowCursor")
                    if (q == "q1") {
                        preferences[q1CursorKey] ?: 0
                    } else {
                        preferences[q2CursorKey] ?: 0
                    }
                }
                flow.collect {
                    logi("$it")
                    nowCursor = it
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun writeNowCursor() {
        GlobalScope.launch {
            dataStore.edit {
                if (q == "q1") {
                    it[q1CursorKey] = nowCursor
                } else {
                    it[q2CursorKey] = nowCursor
                }
            }
        }
    }

    private fun readFileInfo(bufferedReader: BufferedReader, q: String = "") {
        var str: String? = ""

        var title = ""
        var items = mutableListOf<String>()
        var answer = ""

        var needNextTitle = false


        while (bufferedReader.readLine().also { str = it } != null) {
            str?.let { s ->

                if (isTitle(s)) {
                    index++
                    title = replaceHeader(s)
                    synchronized(keyMap) {

                        if (keyMap.containsKey(title)) {
                            keyMap[title] = keyMap[title]!! + 1
                            needNextTitle = true
                            return@let
                        } else {
                            needNextTitle = false
                            items = mutableListOf()
                            keyMap[title] = 1
                        }
                    }

                }
                //在下一个题目出现前，item 和 answer不搜集

                if (needNextTitle) return@let

                if (isItems(s)) {
                    items.add(s)
                }

                if (isAnswer(s)) {
                    answer = s
                    //出现答案时，说明已经到这一题的最后了
                    val aAndQ = AAndQ(
                        title, items, answer
                    )
                    list.add(aAndQ)
                }
            }
        }
    }

    private val headers = listOf("1、","2、","3、","4、","5、","6、",
        "7、","8、","9、","10、","11、","12、",
        "13、","14、","15、","16、","17、","18、",
        )
    private fun replaceHeader(s: String): String {
        var ret = ""
        for (header in headers) {
            ret = s.replace(header, "")
        }
        return ret
    }

    @SuppressLint("SetTextI18n")
    private fun showView() {
        try {
            if (list.isEmpty()) return
            clearItemsView()
            with(binding) {
                val aAndQ = list[nowCursor]
                title.text = aAndQ.title

                if (aAndQ.items.isNotEmpty()) item1.text = aAndQ.items[0]
                if (aAndQ.items.size > 1) item2.text = aAndQ.items[1]
                if (aAndQ.items.size > 2) item3.text = aAndQ.items[2]
                if (aAndQ.items.size > 3) item4.text = aAndQ.items[3]
                if (aAndQ.items.size > 4) item5.text = aAndQ.items[4]
                if (aAndQ.items.size > 5) item6.text = aAndQ.items[5]

                answer.text = aAndQ.answer

                questionCount.text = "${nowCursor + 1}/${list.size}，去重前题数$index"

//                repeatTimes
                repeatTimes.text = "出现次数 ${keyMap[replaceHeader(aAndQ.title)]}"
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun clearItemsView() {
        with(binding) {
            item1.text = ""
            item2.text = ""
            item3.text = ""
            item4.text = ""
            item5.text = ""
            item6.text = ""
        }
    }


    private var nowCursor = 0

    private fun initListener() {
        binding.prev.setOnClickListener {
            nowCursor--
            if (nowCursor < 0)
                nowCursor = list.size - 1
            writeNowCursor()
            showView()
        }
        binding.next.setOnClickListener {
            nowCursor++
            if (nowCursor > list.size - 1)
                nowCursor = 0
            writeNowCursor()
            showView()
        }

        binding.showAnswer.setOnClickListener {
            binding.answer.visibility = if (binding.answer.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
        }

    }

    private fun isTitle(s: String): Boolean {

        if (s.startsWith("1、") || s.startsWith("2、")
            || s.startsWith("3、") || s.startsWith("4、")
            || s.startsWith("5、") || s.startsWith("6、")
            || s.startsWith("7、") || s.startsWith("8、")
            || s.startsWith("9、") || s.startsWith("10、")
            || s.startsWith("11、") || s.startsWith("12、")
            || s.startsWith("13、") || s.startsWith("14、")
            || s.startsWith("15、") || s.startsWith("16、")) {
            return true
        }
        return false
    }
    private fun isItems(s: String): Boolean {
        if (s.startsWith("\uF0B7 ")) {
            return true
        }
        return false
    }
    private fun isAnswer(s: String): Boolean {
        if (s.startsWith("标准答案")) {
            return true
        }
        return false
    }

}

val Int.dp: Int get() = (this / getSystem().displayMetrics.density).toInt()