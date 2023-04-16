package com.example.fakestore.UI.Product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.Data.remote.Model.StoreModelItem
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

    private val _storeModelItem = MutableLiveData<List<StoreModelItem>>()
    val storeModelItemList : MutableLiveData<List<StoreModelItem>> get() = _storeModelItem

    init {
        getData()
    }

    private fun getData(){
        viewModelScope.launch {
            databaseUseCase.getAll().forEach {
                _storeModelItem.value = listOf(apiUseCase.getOneData(it.idStore!!))
            }
        }
    }
}