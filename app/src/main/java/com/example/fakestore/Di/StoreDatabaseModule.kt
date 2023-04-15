package com.example.fakestore.Di

import android.content.Context
import androidx.room.Room
import com.example.fakestore.Data.local.Dao.IStoreDao
import com.example.fakestore.Data.local.Database.StoreDatabase
import com.example.fakestore.Data.local.repository.DatabaseRepositorImpl
import com.example.fakestore.Data.local.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StoreDatabaseModule {

    @Singleton
    @Provides
    fun provideStoreDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        StoreDatabase::class.java,
        "storeDB"
    ).build()

    @Singleton
    @Provides
    fun provideStoreDao(storeDatabase: StoreDatabase) = storeDatabase.storeDao()

    @Singleton
    @Provides
    fun provideStoreDatabaseRepository(storeDao: IStoreDao) : DatabaseRepository {
        return DatabaseRepositorImpl(storeDao)
    }
}