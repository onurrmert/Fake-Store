package com.example.fakestore.Data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fakestore.Data.local.Entity.StoreEntity
import org.jetbrains.annotations.NotNull

@Dao
interface IStoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(storeModel: StoreEntity)

    @Query("SELECT * FROM storeTable")
    suspend fun getAll() : List<StoreEntity>

    @Query("DELETE FROM storeTable WHERE id=:id")
    suspend fun delete(id : Int)
}