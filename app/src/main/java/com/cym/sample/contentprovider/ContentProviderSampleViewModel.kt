package com.cym.sample.contentprovider

import android.provider.UserDictionary
import androidx.lifecycle.ViewModel

/**
 * Created by caoj on 2022/10/19.
 */
class ContentProviderSampleViewModel: ViewModel() {
    private val mProjection: Array<String> = arrayOf(
        UserDictionary.Words._ID,
        UserDictionary.Words.WORD,
        UserDictionary.Words.LOCALE
    )

    private var selectionClause: String? = null

    private lateinit var selectionArgs: Array<String>

    
}