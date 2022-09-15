package com.cym.sample.persistence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.preference.Preference
import com.cym.utilities.logi
import com.xmcc.androidbasesample.databinding.ActivityDataStoreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        clickListenerInit()

    }

    private fun clickListenerInit() {
        binding.buttonRead.setOnClickListener {
            readPreferencesDataStore()
        }
        binding.buttonWrite.setOnClickListener {
            GlobalScope.launch {
                writePreferencesDataStore()
            }
        }
    }

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
    private fun readPreferencesDataStore() {
        //读取
        val exampleCounterFlow: Flow<Int> = dataStore.data.map { preferences ->
            preferences[EXAMPLE_COUNTER] ?: 0
        }
        GlobalScope.launch {
            logi("read begin")
            exampleCounterFlow.collect {
                logi("collect $it")
            }
//            logi("read ${exampleCounterFlow.count()}")
            logi("read end")
        }
    }

    private suspend fun writePreferencesDataStore() {
        //写入
        dataStore.edit {
            val currentCounterValue = it[EXAMPLE_COUNTER] ?: 0
            it[EXAMPLE_COUNTER] = currentCounterValue + 1
        }

        dataStore.data.collect {
            val count = it[EXAMPLE_COUNTER] ?: 0
            logi(count)
        }
    }

    private fun protoDataStore() {

    }
}