package com.example.fakestore.UI.Mens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakestore.Data.Model.StoreModel
import com.example.fakestore.Domain.StoreApiUseCase
import com.example.fakestore.Util.Constant
import com.example.fakestore.Util.Constant.Companion.MensUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MensViewModel @Inject constructor(
    private val storeApiUseCase: StoreApiUseCase
) : ViewModel(){

    private val _storeModelItem = MutableLiveData<StoreModel>()

    val storeModelItem : MutableLiveData<StoreModel> get() = _storeModelItem

    init {
        getMen()
    }

    fun getMen(){
        CoroutineScope(Dispatchers.Main).launch {
            _storeModelItem.value = storeApiUseCase.getCategory(MensUrl)
        }
    }
}