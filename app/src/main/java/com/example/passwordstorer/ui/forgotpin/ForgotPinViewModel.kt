package com.example.passwordstorer.ui.forgotpin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordstorer.datastore.DataStoreManager
import com.example.passwordstorer.room.entity.PinEntity
import com.example.passwordstorer.room.repository.AppDataBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPinViewModel @Inject constructor(
    private val appDataBaseRepository: AppDataBaseRepository,
    private val dataStoreManager: DataStoreManager
): ViewModel(){

    var insertResultLiveData = MutableLiveData<Long>()

    fun insertPin(pin: PinEntity) {
        var result: Long = -1
        viewModelScope.launch {
            result = appDataBaseRepository.insertPin(pin)
            insertResultLiveData.value = result
        }
    }

}