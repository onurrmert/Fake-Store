package com.example.fakestore.Data.Repository

import com.example.fakestore.Data.Api.IStoreApi
import com.example.fakestore.Data.Model.StoreModel
import retrofit2.Response
import javax.inject.Inject

class StoreApiRespositoryImpl @Inject constructor(
    private val storeApi : IStoreApi
) : StoreApiRepository{

    override suspend fun getCategory(category: String): StoreModel {
        return storeApi.getCategory(category).body()!!
    }
}