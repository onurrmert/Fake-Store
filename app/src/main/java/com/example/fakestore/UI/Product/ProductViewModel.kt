package com.example.fakestore.UI.Product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakestore.Data.local.Entity.StoreEntity
import com.example.fakestore.Data.remote.Model.StoreModelItem
import com.example.fakestore.Domain.DatabaseUseCase
import com.example.fakestore.Domain.StoreApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val apiUseCase: StoreApiUseCase,
    private val databaseUseCase: DatabaseUseCase,
): ViewModel() {

    val storeModelItemList = MutableLiveData<List<StoreModelItem>>()

    private val _storeEntity = MutableLiveData<List<StoreEntity>>()

    val storeEntity : MutableLiveData<List<StoreEntity>> get() = _storeEntity

    init {
        getData()
        getStoreEntityList()
    }

    private fun getData(){
        val storeItemList = ArrayList<StoreModelItem>()
        CoroutineScope(Dispatchers.Main).launch {
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

    private fun getStoreEntityList(){
        CoroutineScope(Dispatchers.Main).launch {
            _storeEntity.value = databaseUseCase.getAll()
        }
    }

    fun delete(id : Int){
        CoroutineScope(Dispatchers.Main).launch {
            databaseUseCase.delete(id)
            getData()
        }
    }
}