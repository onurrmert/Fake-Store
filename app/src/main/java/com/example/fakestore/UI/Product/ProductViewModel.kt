package com.example.fakestore.UI.Product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.Domain.DatabaseUseCase
import com.example.fakestore.Domain.StoreApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val apiUseCase: StoreApiUseCase,
    private val databaseUseCase: DatabaseUseCase
): ViewModel() {

    val _id = MutableLiveData<Int>()
    val id : MutableLiveData<Int> get() = _id

    fun getData(){
        viewModelScope.launch {
            databaseUseCase.getAll().forEach {
                println(apiUseCase.getOneData(it.idStore!!).image)
            }
        }
    }
}