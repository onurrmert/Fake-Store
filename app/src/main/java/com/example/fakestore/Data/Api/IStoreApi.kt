package com.example.fakestore.Data.Api

import com.example.fakestore.Data.Model.StoreModel
import com.example.fakestore.Data.Model.StoreModelItem
import com.example.fakestore.Util.Constant
import com.example.fakestore.Util.Constant.Companion.BaseUrl
import com.example.fakestore.Util.Constant.Companion.CategoryUrl
import com.example.fakestore.Util.Constant.Companion.ProductUrl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IStoreApi {

    @GET(BaseUrl + CategoryUrl + "{Category}")
    suspend fun getCategory(@Path("Category") Category : String) : Response<StoreModel>

    @GET(BaseUrl + ProductUrl + "{id}")
    suspend fun getOneData(@Path("id") id : Int) : Response<StoreModelItem>
}