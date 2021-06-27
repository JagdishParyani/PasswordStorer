package com.example.passwordstorer.common.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.passwordstorer.R
import java.lang.Exception

class AppPreferences(private val context: Context) {

    private var PRIVATE_MODE = 0
    private val PREF_NAME = context.getString(R.string.app_name) + "_sharedPrefs".trim()

    private val sharedPref: SharedPreferences

    init {
        sharedPref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    fun getSharedPrefsInstance(): SharedPreferences {
        return sharedPref
    }

    fun <T> put(key: String, value: T) {
        getSharedPrefsInstance().edit().apply {
            when (value) {
                is String -> putString(key,value)
                is Boolean -> putBoolean(key, value)
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> throw Exception("Undefined type")
            }.apply()
        }
    }

    fun <T> get(key: String, defaultValue: T): Any {
        getSharedPrefsInstance().apply {
           return when (defaultValue) {
               is String -> getString(key, defaultValue)
               is Boolean -> getBoolean(key, defaultValue)
               is Int -> getInt(key, defaultValue)
               is Float -> getFloat(key, defaultValue)
               is Long -> getLong(key, defaultValue)
               else -> throw Exception("Undefined type")
           }!!
        }
    }
}