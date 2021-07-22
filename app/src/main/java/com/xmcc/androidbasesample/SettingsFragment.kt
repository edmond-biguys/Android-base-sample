package com.xmcc.androidbasesample

import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val signature = findPreference<EditTextPreference>("signature")
        println("caoj signature.text ${signature?.text}")
        val listener: Preference.OnPreferenceClickListener = Preference.OnPreferenceClickListener {
            println("caoj 1111 ${it.key} ${it.sharedPreferences.getString(it.key, "abc")}")
            true
        }
        signature?.onPreferenceClickListener = listener
        signature?.setOnPreferenceClickListener {
            println("caoj ${it.key}")
            true
        }
    }
}