package com.example.fakestore.UI.Womens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakestore.Data.remote.Model.StoreModel
import com.example.fakestore.Domain.StoreApiUseCase
import com.example.fakestore.Util.Constant.Companion.WomenUrl
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
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _storeModelItem.value = storeApiUseCase.getCategory(WomenUrl)
            }catch (e : Exception){
                Log.e("getWomen error: " , e.localizedMessage)
            }
        }
    }
}