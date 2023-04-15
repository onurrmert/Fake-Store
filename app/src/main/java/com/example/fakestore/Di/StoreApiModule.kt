package com.example.fakestore.Di

import com.example.fakestore.Data.remote.Api.IStoreApi
import com.example.fakestore.Data.remote.Repository.StoreApiRepository
import com.example.fakestore.Data.remote.Repository.StoreApiRespositoryImpl
import com.example.fakestore.Util.Constant.Companion.BaseUrl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StoreApiModule {

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    @Singleton
    @Provides
    fun getStoreApi() : IStoreApi {
        return StoreApiModule().getRetrofit().create(IStoreApi::class.java)
    }

    @Singleton
    @Provides
    fun provideStoreApiRepository(storeApi: IStoreApi) : StoreApiRepository {
        return StoreApiRespositoryImpl(storeApi)
    }
}