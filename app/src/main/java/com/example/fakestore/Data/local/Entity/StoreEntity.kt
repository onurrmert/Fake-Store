package com.example.fakestore.Data.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storeTable")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "idStore")
    val idStore: Int?,

){
    constructor(idStore: Int?) : this(0, idStore)
}
