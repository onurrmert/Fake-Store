package com.example.fakestore.UI.Electronics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakestore.Data.remote.Model.StoreModel
import com.example.fakestore.Domain.StoreApiUseCase
import com.example.fakestore.Util.Constant.Companion.ElectricUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElectronicsViewModel @Inject constructor(
    private val storeApiUseCase: StoreApiUseCase
) : ViewModel() {

    private val _storeModelItem = MutableLiveData<StoreModel>()

    val storeModelItem : MutableLiveData<StoreModel> get() = _storeModelItem

    init {
        getElectronics()
    }

    private fun getElectronics(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _storeModelItem.value = storeApiUseCase.getCategory(ElectricUrl)
            }catch (e : Exception){
                Log.e("getElectronics error: " , e.localizedMessage)
            }
        }
    }
}