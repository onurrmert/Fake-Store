package com.example.fakestore.UI.Detail

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
class DetailViewModel @Inject constructor(
    private val apiUseCase: StoreApiUseCase,
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    private val _storeModelItem = MutableLiveData<StoreModelItem>()

    val storeModelItem : MutableLiveData<StoreModelItem> get() = _storeModelItem

    fun getOneData(id : Int){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _storeModelItem.value = apiUseCase.getOneData(id)
            }catch (e : Exception){
                Log.e(" Detail viewModel getOneData error:", e.localizedMessage)
            }
        }
    }

    fun insert(id: Int){
        CoroutineScope(Dispatchers.Main).launch {
            databaseUseCase.insert(StoreEntity(id))
        }
    }
}