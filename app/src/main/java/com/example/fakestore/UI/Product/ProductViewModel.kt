package com.example.fakestore.UI.Product

import android.util.Log
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

    val storeModelItemList = MutableLiveData<List<StoreModelItem>>()

    init {
        getData()
    }

    private fun getData(){
        val storeItemList = ArrayList<StoreModelItem>()
        viewModelScope.launch {
            databaseUseCase.getAll().forEach {
               try {
                   storeItemList.add(apiUseCase.getOneData(it.idStore!!))
               }catch (e: java.lang.Exception){
                   Log.e("product view model getData: ", e.localizedMessage)
               }
            }
            storeModelItemList.value = storeItemList
        }
    }
}