package com.xmcc.androidbasesample.device.bluetooth

import android.app.ListActivity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothAdapter.LeScanCallback
import android.os.Handler


/**
 * Created by caoj on 2021/5/28.
 */
class DeviceScanActivity : ListActivity() {
    var bluetoothAdapter: BluetoothAdapter? = null
    protected var mScanning = false
    private var handler: Handler = Handler()
    protected var rsiAverage = 0
    protected var block = false
    private val rsiList: MutableList<Int> = ArrayList()

    //private BluetoothDevice mBluetoothDevice;
    private var mBle: Ble? = null
    fun scanLeDevice(enable: Boolean) {
        block = false
        rsiList.clear()
        if (enable) {
            // Stops scanning after a pre-defined scan period.
//            handler = Handler()
            handler.postDelayed(Runnable {
                mScanning = false
                bluetoothAdapter!!.stopLeScan(leScanCallback)
                rsiAverage = getRsiAverageValue()
                if (rsiAverage < -70) {
                    //信号弱于-70db时，启动block，触发回调函数，启动摄像头拍照。
                    mBle!!.doBlock()
                } else if (rsiAverage == 0) {
                    println("=====There is no ble device,please check=====")
                } else {
                    println("=====scanLeDevice()=====")
                    scanLeDevice(true)
                }
            }, SCAN_PERIOD)
            mScanning = true
            bluetoothAdapter!!.startLeScan(leScanCallback)
        } else {
            mScanning = false
            bluetoothAdapter!!.stopLeScan(leScanCallback)
        }
    }

    private val leScanCallback =
        LeScanCallback { device, rssi, scanRecord ->
            runOnUiThread {
                println("leScanCallback $device $rssi")
                if (device.address == "4C:63:71:1F:A2:EE") {
                    println("device name: " + device.name + ",   device address: " + device.address + ", rssi: " + rssi)
                    rsiList.add(rssi)
                }
            }
        }

    fun getRsiAverageValue(): Int {
        var sum = 0
        var num = 1
        var avrage = 0
        for (j in rsiList.indices) {
            sum = sum + rsiList[j]
        }
        num = rsiList.size
        if (rsiList.size == 0) {
            num = 1
            bluetoothAdapter!!.stopLeScan(leScanCallback)
        }
        avrage = sum / num
        println("=====getRsiAverage=====")
        println("avrage: $avrage")
        return avrage
    }

    fun makeBleInstance() {
        mBle = Ble()
        mBle!!.setBleListener(object : BleListener {
            override fun block() {
                println("a car is in the Parking space!")
                println("Now turn on the camera to shoot!")
            }
        })
    }

    //回调函数接口
    interface BleListener {
        fun block()
    }

    class Ble {
        private var mBleListener: BleListener? = null
        fun setBleListener(mBleListener: BleListener?) {
            this.mBleListener = mBleListener
        }

        fun doBlock() {
            mBleListener!!.block()
        }
    }

    companion object {
        // Stops scanning after 10 seconds.
        private const val SCAN_PERIOD: Long = 5000
    }
}