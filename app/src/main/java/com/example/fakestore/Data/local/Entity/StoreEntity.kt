package com.example.fakestore.Data.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storeTable")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "idStore")
    val idStore: Int?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "price")
    val price: Double?,

    @ColumnInfo(name = "title")
    val title: String?
)
