package com.example.fakestore.Data.local.repository

import com.example.fakestore.Data.local.Entity.StoreEntity

interface DatabaseRepository {

    suspend fun insert(storeEntity: StoreEntity)

    suspend fun getAll() : List<StoreEntity>

    suspend fun delete(id : Int)
}