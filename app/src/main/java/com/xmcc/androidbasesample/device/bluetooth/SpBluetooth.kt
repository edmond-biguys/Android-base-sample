package com.xmcc.androidbasesample.device.bluetooth

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.xmcc.androidbasesample.App

/**
 * Created by caoj on 2021/5/30.
 */
object SpBluetooth {

    val sp = App.getApp().getSharedPreferences("config", Context.MODE_PRIVATE)
    fun initMacList() {
        val edit = sp.edit()
        var macBean = BluetoothMacList()
        if (sp.getString("mac", "") == "") {

        } else {
            macBean = Gson().fromJson(sp.getString("mac", ""), BluetoothMacList::class.java)
            this.macList.addAll(macBean.macList)
        }

    }
    @SuppressLint("ApplySharedPref")
    fun saveMac(mac: String) {
        val edit = sp.edit()
        var macBean = BluetoothMacList()
        if (sp.getString("mac", "") == "") {

        } else {
            macBean = Gson().fromJson(sp.getString("mac", ""), BluetoothMacList::class.java)
        }
        if (!macBean.macList.contains(mac)) {
            macBean.macList.add(mac)
            this.macList.clear()
            this.macList.addAll(macBean.macList)
        }
        edit.putString("mac", Gson().toJson(macBean))
        edit.commit()

    }
    @SuppressLint("ApplySharedPref")
    fun delMac(mac: String) {
        val edit = sp.edit()
        var macBean = BluetoothMacList()
        if (sp.getString("mac", "") == "") {

        } else {
            macBean = Gson().fromJson(sp.getString("mac", ""), BluetoothMacList::class.java)
        }
        if (macBean.macList.contains(mac)) {


            this.macList.clear()
            this.macList.addAll(macBean.macList)
        }
        edit.putString("mac", Gson().toJson(macBean))
        edit.commit()

    }

    val macList = ArrayList<String>()
    fun hasMac(mac: String): Boolean {
        try {
            return macList.contains(mac)
        } catch (e: Exception) {
            return false
        }
    }

    fun clearMac() {
        val edit = sp.edit()
        var macBean = BluetoothMacList()
        edit.putString("mac", "")
    }
}