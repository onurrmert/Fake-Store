package com.example.fakestore.UI.Detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.Data.Model.StoreModel
import com.example.fakestore.Data.Model.StoreModelItem
import com.example.fakestore.Domain.StoreApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiUseCase: StoreApiUseCase
) : ViewModel() {

    private val _storeModelItem = MutableLiveData<StoreModelItem>()

    val storeModelItem : MutableLiveData<StoreModelItem> get() = _storeModelItem

    fun getOneData(id : Int){
        viewModelScope.launch {
            _storeModelItem.value = apiUseCase.getOneData(id)
        }
    }
}