package com.example.passwordstorer.ui.login

import androidx.lifecycle.*
import com.example.passwordstorer.datastore.DataStoreManager
import com.example.passwordstorer.room.repository.AppDataBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appDataBaseRepository: AppDataBaseRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val getPinLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun isPinEqual(pin: String){
        viewModelScope.launch {
            val dbPinValue = appDataBaseRepository.getPinFromDb()
            getPinLiveData.value = dbPinValue == pin
        }
    }

}