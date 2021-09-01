package com.xmcc.androidbasesample

import android.app.Application
import android.content.Intent
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants
import com.xmcc.androidbasesample.device.bluetooth.BluetoothService


/**
 * Created by caoj on 2021/5/28.
 */
class App: TinkerApplication(ShareConstants.TINKER_ENABLE_ALL, "com.xmcc.androidbasesample.AppLike",
    "com.tencent.tinker.loader.TinkerLoader", false) {

}