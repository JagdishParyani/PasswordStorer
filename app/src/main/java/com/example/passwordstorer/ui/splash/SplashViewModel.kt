package com.example.passwordstorer.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.passwordstorer.common.utils.PreferenceKeys
import com.example.passwordstorer.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val isPinSetUpLiveData = liveData(Dispatchers.IO) {
        dataStoreManager.getValue(PreferenceKeys.IS_PIN_SETUP, false).collect {
            emit(it)
        }
    }

    val isBiometricSetUpLiveData: LiveData<Boolean> = liveData(Dispatchers.IO) {
        dataStoreManager.getValue(PreferenceKeys.IS_BIOMETRIC_SETUP, false).collect {
            emit(it)
        }
    }
}