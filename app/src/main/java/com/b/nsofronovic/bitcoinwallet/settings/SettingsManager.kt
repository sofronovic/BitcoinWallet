package com.b.nsofronovic.bitcoinwallet.settings

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

class SettingsManager @Inject constructor(private val context: Context) {

    fun setString(key: String, value: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, null)
    }

    fun setBoolean(key: String, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean? {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false)
    }
}