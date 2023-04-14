package com.example.fakestore.Data.Api

import com.example.fakestore.Data.Model.StoreModel
import com.example.fakestore.Util.Constant.Companion.BaseUrl
import com.example.fakestore.Util.Constant.Companion.CategoryUrl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IStoreApi {
    @GET(BaseUrl + CategoryUrl + "{Category}")
    suspend fun getCategory(@Path("Category") Category : String) : Response<StoreModel>
}