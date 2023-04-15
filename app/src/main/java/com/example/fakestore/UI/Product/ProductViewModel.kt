package com.example.fakestore.UI.Product

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fakestore.Domain.StoreApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val apiUseCase: StoreApiUseCase
): ViewModel() {

    val _id = MutableLiveData<Int>()
    val id : MutableLiveData<Int> get() = _id

    fun getData(){

    }
}