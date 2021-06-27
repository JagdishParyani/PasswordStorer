package com.example.passwordstorer.common.utils

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceKeys {
    val IS_PIN_SETUP : Preferences.Key<Boolean> = booleanPreferencesKey("is_pin_setup")
    val IS_BIOMETRIC_SETUP : Preferences.Key<Boolean> = booleanPreferencesKey("is_biometric_setup")

}