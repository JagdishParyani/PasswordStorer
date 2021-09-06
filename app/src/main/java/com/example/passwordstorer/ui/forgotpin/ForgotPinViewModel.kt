package com.example.passwordstorer.ui.forgotpin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordstorer.room.entity.PinEntity
import com.example.passwordstorer.room.repository.pin.PinDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPinViewModel @Inject constructor(
    private val pinDBRepo: PinDBRepo
) : ViewModel() {

    var insertResultLiveData = MutableLiveData<Long>()

    fun updatePin(newPin: PinEntity) {
        var result: Long = -1
        viewModelScope.launch {
            val oldPinString = pinDBRepo.getPinFromDb()
            val oldPin = PinEntity(oldPinString)
            result = pinDBRepo.updatePin(oldPin, newPin)
            insertResultLiveData.value = result
        }
    }

}