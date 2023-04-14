package com.example.fakestore.Data.Repository

import com.example.fakestore.Data.Model.StoreModel
import com.example.fakestore.Data.Model.StoreModelItem

interface StoreApiRepository {

    suspend fun getCategory(category : String) : StoreModel

    suspend fun getOneData(id : Int) : StoreModelItem
}