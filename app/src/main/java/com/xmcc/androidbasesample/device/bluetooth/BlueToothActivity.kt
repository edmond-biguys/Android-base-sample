package com.xmcc.androidbasesample.device.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.xmcc.androidbasesample.databinding.ActivityBlueToothBinding
import kotlin.math.max



class BlueToothActivity : AppCompatActivity() {
    lateinit var binding: ActivityBlueToothBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBlueToothBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                permissions, 123)
        }
        binding.buttonStartDiscovery.setOnClickListener {
            val discoverableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
                putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 150)
            }
            startActivity(discoverableIntent)
        }


        val listener = View.OnClickListener { v -> v.id }
        binding.buttonStartDiscovery.setOnClickListener(listener)
        binding.buttonStartDiscovery.setOnClickListener {  }
        binding.buttonStartDiscovery.setOnClickListener({})


        Thread {
            Thread.sleep(10_000)
            println("start discoverable")
            val discoverableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
                putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 150)
            }
            startActivity(discoverableIntent)
        }.start()

//        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
////        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
//        registerReceiver(receiver, filter)
        println("blue tooth")

//        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
//        Thread {
//            while (flag) {
//                println("startDiscovery")
//                mBluetoothAdapter.startDiscovery()
//                Thread.sleep(5_000)
//                mBluetoothAdapter.cancelDiscovery()
//            }
//        }.start()

        //实例化扫描类
        //实例化扫描类
    }





    var flag = true
    override fun onDestroy() {
        super.onDestroy()
        flag = false
//        unregisterReceiver(receiver)
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
                    val rssi = intent.extras?.getShort(BluetoothDevice.EXTRA_RSSI)
                    println("name $deviceName address $deviceAddress rssi $rssi")
                    save(deviceAddress)
//                    if (deviceAddress == "4C:63:71:1F:A2:EE") {
//                        val readRemoteRssi =
//                            device?.connectGatt(context, false, bluetoothGatt)?.readRemoteRssi()
//                        println("readRemoteRssi $readRemoteRssi")
//                    }
                }

                BluetoothDevice.ACTION_ACL_CONNECTED -> {
//                    intent.getP
                }
            }
        }
    }

    private fun save(deviceAddress: String?) {
        //redmi 4C:63:71:1F:A2:EE
        if (deviceAddress == "4C:63:71:1F:A2:EE") {

        }
    }

    private val bluetoothGatt: BluetoothGattCallback = object : BluetoothGattCallback() {
        override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
            super.onReadRemoteRssi(gatt, rssi, status)
            println("onReadRemoteRssi $rssi")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("onActivityResult requestCode $requestCode resultCode $resultCode ")
    }
}