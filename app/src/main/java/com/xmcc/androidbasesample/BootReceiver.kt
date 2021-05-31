package com.xmcc.androidbasesample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.xmcc.androidbasesample.device.bluetooth.BluetoothService


/**
 * Created by caoj on 2021/5/30.
 */
class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        when(action) {
            Intent.ACTION_BOOT_COMPLETED -> {

                //com.xmcc.androidbasesample.free.debug
                context.startService(Intent(context, BluetoothService::class.java))
            }
        }
    }
}