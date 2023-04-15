package com.example.fakestore.Data.local.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fakestore.Data.local.Dao.IStoreDao
import com.example.fakestore.Data.local.Entity.StoreEntity

@Database(entities = arrayOf(StoreEntity::class), version = 1, exportSchema = false)
abstract class StoreDatabase () : RoomDatabase(){
    abstract fun storeDao (): IStoreDao
}