package com.example.fakestore.Data.remote.Api

import com.example.fakestore.Data.remote.Model.StoreModel
import com.example.fakestore.Data.remote.Model.StoreModelItem
import com.example.fakestore.Util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IStoreApi {

    @GET(Constant.BaseUrl + Constant.CategoryUrl + "{Category}")
    suspend fun getCategory(@Path("Category") Category : String) : Response<StoreModel>

    @GET(Constant.BaseUrl + Constant.ProductUrl + "{id}")
    suspend fun getOneData(@Path("id") id : Int) : Response<StoreModelItem>
}