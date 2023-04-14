package com.example.fakestore.Data.Repository

import com.example.fakestore.Data.Model.StoreModel

interface StoreApiRepository {
    suspend fun getCategory(category : String) : StoreModel
}