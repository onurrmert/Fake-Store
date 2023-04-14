package com.example.fakestore.UI.Womens

import androidx.lifecycle.ViewModel
import com.example.fakestore.Domain.StoreApiUseCase
import com.example.fakestore.Util.Constant
import com.example.fakestore.Util.Constant.Companion.WomenUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WomenViewModel @Inject constructor(
    private val storeApiUseCase: StoreApiUseCase
) : ViewModel(){

    init {
        getWomen()
    }

    fun getWomen(){
        CoroutineScope(Dispatchers.IO).launch {
            storeApiUseCase.getCategory(WomenUrl).forEach {
                println(it.description)
            }
        }
    }
}