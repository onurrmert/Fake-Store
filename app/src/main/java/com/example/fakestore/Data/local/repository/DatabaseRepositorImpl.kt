package com.example.fakestore.Data.local.repository

import com.example.fakestore.Data.local.Dao.IStoreDao
import com.example.fakestore.Data.local.Entity.StoreEntity
import javax.inject.Inject

class DatabaseRepositorImpl @Inject constructor(
    private val storeDao: IStoreDao
): DatabaseRepository {

    override suspend fun insert(storeEntity: StoreEntity) {
        storeDao.insert(storeEntity)
    }

    override suspend fun getAll(): List<StoreEntity> {
        return storeDao.getAll()
    }

    override suspend fun delete(id: Int) {
        storeDao.delete(id)
    }
}