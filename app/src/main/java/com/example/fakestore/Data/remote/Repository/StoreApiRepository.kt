package com.example.fakestore.Data.remote.Repository

import com.example.fakestore.Data.remote.Model.StoreModel
import com.example.fakestore.Data.remote.Model.StoreModelItem

interface StoreApiRepository {

    suspend fun getCategory(category : String) : StoreModel

    suspend fun getOneData(id : Int) : StoreModelItem
}