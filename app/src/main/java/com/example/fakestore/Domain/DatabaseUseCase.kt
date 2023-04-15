package com.example.fakestore.Domain

import com.example.fakestore.Data.local.Entity.StoreEntity
import com.example.fakestore.Data.local.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val repository: DatabaseRepository
) {

    suspend fun insert(storeEntity: StoreEntity){
        repository.insert(storeEntity)
    }

    suspend fun getAll() : List<StoreEntity>{
        return repository.getAll()
    }
}