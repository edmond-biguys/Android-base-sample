package com.xmcc.androidbasesample.device.bluetooth

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.orhanobut.logger.CsvFormatStrategy
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger


class BluetoothService : Service() {

    companion object {
        const val ACTION_RECEIVE_RUNNING_DATA = "com.infi.lift.Receive_Running_Data"
        const val ACTION_RECEIVE_ADD_MAC = "com.infi.lift.Receive_Add_Mac"
        const val ACTION_RECEIVE_DEL_MAC = "com.infi.lift.Receive_Del_Mac"
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        SpBluetooth.initMacList()
        initBluetooth()
    }

    val logger = DiskLogAdapter()
    private fun initBluetooth() {
        Logger.addLogAdapter(logger)
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
//        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
        filter.addAction(ACTION_RECEIVE_RUNNING_DATA)
        filter.addAction(ACTION_RECEIVE_ADD_MAC)
        filter.addAction(ACTION_RECEIVE_DEL_MAC)
        registerReceiver(receiver, filter)
        println("blue tooth")

        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        Thread {
            while (flag) {
//                println("startDiscovery")
                mBluetoothAdapter.startDiscovery()
                scanLeDevice(true)
                Thread.sleep(6_000)
                mBluetoothAdapter.cancelDiscovery()
            }
        }.start()




    }

    var flag = true
    override fun onDestroy() {
        super.onDestroy()
        flag = false
        unregisterReceiver(receiver)
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            println("action $action")
            when(action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceAddress = device?.address
                    val deviceName = device?.name
                    val rssi = intent.extras?.getShort(BluetoothDevice.EXTRA_RSSI)?.toInt()
                    println("name $deviceName address $deviceAddress rssi $rssi")
                    save(deviceAddress, deviceName, rssi)
//                    if (deviceAddress == "4C:63:71:1F:A2:EE") {
//                        val readRemoteRssi =
//                            device?.connectGatt(context, false, bluetoothGatt)?.readRemoteRssi()
//                        println("readRemoteRssi $readRemoteRssi")
//                    }
                }

                BluetoothDevice.ACTION_ACL_CONNECTED -> {
//                    intent.getP
                }

                ACTION_RECEIVE_ADD_MAC -> {
                    val mac = intent.getStringExtra("mac")!!
                    SpBluetooth.saveMac(mac)
                }
                ACTION_RECEIVE_DEL_MAC -> {
                    val mac = intent.getStringExtra("mac")!!
                    SpBluetooth.delMac(mac)
                }

                ACTION_RECEIVE_RUNNING_DATA -> {
                    RunningData.doorStatus = intent.getIntExtra("doorStatus", 0)
                    RunningData.speed = intent.getDoubleExtra("speed", 0.0)
                    RunningData.hasPerson = intent.getIntExtra("hasPerson", 0)
                    RunningData.floorNum = intent.getIntExtra("floorNum", 0)
                    RunningData.floorAlias = intent.getStringExtra("floorAlias")!!
                }
            }
        }
    }


    private fun save(deviceAddress: String?, deviceName: String?, rssi: Int?) {
       try {
           //redmi 4C:63:71:1F:A2:EE

           Log.d("bluetoothDetecting", "has mac ${SpBluetooth.hasMac(deviceAddress!!)}")
           if (deviceAddress == "4C:63:71:1F:A2:EE" || SpBluetooth.hasMac(deviceAddress)) {
               val printMessage = "bluetooth detecting name:$deviceName mac:$deviceAddress rssi:$rssi doorStatus: ${RunningData.doorStatus} doorAlias:${RunningData.getDoorAlias()} " +
                       "speed:${RunningData.speed} hasPerson:${RunningData.hasPerson} floorNum:${RunningData.floorNum} floorAlias:${RunningData.floorAlias}"

               println(printMessage)
               logger.log(1, "Bluetooth", printMessage)

           }
       } catch (e: Exception) {
           e.printStackTrace()
       }
    }


    private var mScanning: Boolean = false
    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }
    private val SCAN_PERIOD: Long = 10000
    private val handler: Handler = Handler()
    private fun scanLeDevice(enable: Boolean) {
        when (enable) {
            true -> {
                // Stops scanning after a pre-defined scan period.
                handler.postDelayed({
                    mScanning = false
                    bluetoothAdapter?.stopLeScan(leScanCallback)
                }, SCAN_PERIOD)
                mScanning = true
                bluetoothAdapter?.startLeScan(leScanCallback)
            }
            else -> {
                mScanning = false
                bluetoothAdapter?.stopLeScan(leScanCallback)
            }
        }
    }

    private val leScanCallback = BluetoothAdapter.LeScanCallback { device, rssi, scanRecord ->
        println("ble name ${device.name} alias ${device.alias} address ${device.address} rssi $rssi scanRecord ${scanRecord.size}")

    }


}