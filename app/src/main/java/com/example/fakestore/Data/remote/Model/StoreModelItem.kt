package com.example.fakestore.Data.remote.Model

data class StoreModelItem(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val rating: Rating?,
    val title: String?
)