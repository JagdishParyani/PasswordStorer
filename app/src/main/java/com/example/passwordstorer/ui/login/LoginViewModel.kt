package com.example.passwordstorer.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordstorer.room.repository.pin.PinDBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val pinDBRepo: PinDBRepo
) : ViewModel() {

    val getPinLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun isPinEqual(pin: String) {
        viewModelScope.launch {
            val dbPinValue = pinDBRepo.getPinFromDb()
            getPinLiveData.value = dbPinValue == pin
        }
    }

}