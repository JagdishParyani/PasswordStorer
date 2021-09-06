package com.example.passwordstorer.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordstorer.room.entity.CategoryEntity
import com.example.passwordstorer.room.repository.categories.CategoriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val categoriesRepo: CategoriesRepo
) : ViewModel() {

    val categoriesListLiveData: MutableLiveData<List<CategoryEntity>> = MutableLiveData()

    fun getAllCategories() {
        viewModelScope.launch {
            categoriesListLiveData.postValue(categoriesRepo.getAllCategories())
        }
    }

}