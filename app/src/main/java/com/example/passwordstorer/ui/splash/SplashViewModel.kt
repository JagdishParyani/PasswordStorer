package com.example.passwordstorer.ui.splash

import androidx.lifecycle.*
import com.example.passwordstorer.common.utils.PreferenceKeys
import com.example.passwordstorer.common.utils.combineTupleNonNull
import com.example.passwordstorer.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

//    val isPinSetUpLiveData: MutableLiveData<Boolean> = MutableLiveData()
//    val isBiometricSetUpLiveData: MutableLiveData<Boolean> = MutableLiveData()
//
//    init {
//        isPinSetUpLiveData.value = false
//        isBiometricSetUpLiveData.value = false
//        getPinSetUpLiveData()
//        getBiometricSetUpLiveData()
//    }
//
//    fun getPinSetUpLiveData() {
//        viewModelScope.launch {
//            dataStoreManager.getValue(PreferenceKeys.IS_PIN_SETUP, false).collect { pinSetUpValue ->
//                isPinSetUpLiveData.value = pinSetUpValue
//            }
//        }
//    }
//
//    fun getBiometricSetUpLiveData() {
//        viewModelScope.launch {
//            dataStoreManager.getValue(PreferenceKeys.IS_BIOMETRIC_SETUP, false).collect {
//                    biometricSetUpValue ->
//                isBiometricSetUpLiveData.value = biometricSetUpValue
//            }
//        }
//    }

    val isPinSetUpLiveData: LiveData<Boolean> = liveData(Dispatchers.IO) {
        dataStoreManager.getValue(PreferenceKeys.IS_PIN_SETUP, false).collect {
//            (isPinSetUpLiveData as MutableLiveData<Boolean>).value = it
            emit(it)
        }
    }

    val isBiometricSetUpLiveData: LiveData<Boolean> = liveData(Dispatchers.IO) {
        dataStoreManager.getValue(PreferenceKeys.IS_BIOMETRIC_SETUP, false).collect {
            emit(it)
        }
    }

    fun getBothBooleanValues(): LiveData<Pair<Boolean, Boolean>> {
//        getPinSetUpLiveData()
        val isPinSetUpLiveData: LiveData<Boolean> = isPinSetUpLiveData
        val isBiometricSetUpLiveData: LiveData<Boolean> = isBiometricSetUpLiveData

        return combineTupleNonNull(isPinSetUpLiveData, isBiometricSetUpLiveData)
    }
}