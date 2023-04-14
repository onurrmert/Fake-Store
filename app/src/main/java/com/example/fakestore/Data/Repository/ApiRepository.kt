package com.example.fakestore.Data.Repository

import com.example.fakestore.Data.Model.StoreModel

interface ApiRepository {
    suspend fun getCategory(category : String) : StoreModel
}