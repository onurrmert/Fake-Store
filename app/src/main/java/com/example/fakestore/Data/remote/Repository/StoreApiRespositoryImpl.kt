package com.example.fakestore.Data.remote.Repository

import com.example.fakestore.Data.remote.Api.IStoreApi
import com.example.fakestore.Data.remote.Model.StoreModel
import com.example.fakestore.Data.remote.Model.StoreModelItem
import retrofit2.Response
import javax.inject.Inject

class StoreApiRespositoryImpl @Inject constructor(
    private val storeApi : IStoreApi
) : StoreApiRepository {

    override suspend fun getCategory(category: String): StoreModel {
        return storeApi.getCategory(category).body()!!
    }

    override suspend fun getOneData(id: Int): StoreModelItem {
        return storeApi.getOneData(id).body()!!
    }
}