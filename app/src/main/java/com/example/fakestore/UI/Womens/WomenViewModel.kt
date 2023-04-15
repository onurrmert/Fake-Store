package com.example.fakestore.UI.Womens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.Data.Model.StoreModel
import com.example.fakestore.Domain.StoreApiUseCase
import com.example.fakestore.Util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WomenViewModel @Inject constructor(
    private val storeApiUseCase: StoreApiUseCase
) : ViewModel() {

    private val _storeModelItem = MutableLiveData<StoreModel>()

    val storeModelItem : MutableLiveData<StoreModel> get() = _storeModelItem

    init {
        getWomen()
    }

    fun getWomen(){
        viewModelScope.launch {
            _storeModelItem.value = storeApiUseCase.getCategory(Constant.MensUrl)
        }
    }
}