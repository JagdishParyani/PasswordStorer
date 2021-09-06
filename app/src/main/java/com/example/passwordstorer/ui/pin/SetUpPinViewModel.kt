package com.example.passwordstorer.ui.pin

import androidx.lifecycle.*
import com.example.passwordstorer.common.utils.PreferenceKeys
import com.example.passwordstorer.datastore.DataStoreManager
import com.example.passwordstorer.room.entity.PinEntity
import com.example.passwordstorer.room.repository.pin.PinDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetUpPinViewModel @Inject constructor(
    private val pinDBRepo: PinDBRepo,
    private val dataStoreManager: DataStoreManager
) :
    ViewModel() {

    val liveDataPin: LiveData<String> = liveData(Dispatchers.IO) {
        emit(pinDBRepo.getPinFromDb())
    }

    var insertResultLiveData = MutableLiveData<Long>()

    fun insertPin(pin: PinEntity) {
        viewModelScope.launch {
            insertResultLiveData.value = pinDBRepo.insertPin(pin)
        }
    }

    fun updateBiometricAuthentication(isEnable: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setValue(PreferenceKeys.IS_BIOMETRIC_SETUP, isEnable)
        }
    }

    fun updatePinAuthentication(isEnable: Boolean) {
        viewModelScope.launch {
            dataStoreManager.setValue(PreferenceKeys.IS_PIN_SETUP, isEnable)
        }
    }
}