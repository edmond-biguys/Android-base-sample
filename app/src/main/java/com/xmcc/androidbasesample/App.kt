package com.xmcc.androidbasesample

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


/**
 * Created by caoj on 2021/5/28.
 */
@HiltAndroidApp
class App: Application() {

}
//class App: TinkerApplication(ShareConstants.TINKER_ENABLE_ALL, "com.xmcc.androidbasesample.AppLike",
//    "com.tencent.tinker.loader.TinkerLoader", false) {
//
//}
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings2")