package com.example.fakestore.Domain

import com.example.fakestore.Data.remote.Model.StoreModel
import com.example.fakestore.Data.remote.Model.StoreModelItem
import com.example.fakestore.Data.remote.Repository.StoreApiRepository
import javax.inject.Inject

class StoreApiUseCase @Inject constructor(
    private val storeApiRepository: StoreApiRepository
) {

    suspend fun getCategory(category: String) : StoreModel {
        return storeApiRepository.getCategory(category)
    }

    suspend fun getOneData(id : Int) : StoreModelItem {
        return storeApiRepository.getOneData(id)
    }
}