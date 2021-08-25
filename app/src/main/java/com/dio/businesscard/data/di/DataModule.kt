package com.dio.businesscard.data.di

import android.content.Context
import com.dio.businesscard.data.local.LocalDataSource
import com.dio.businesscard.data.local.database.AppDatabase
import com.dio.businesscard.data.local.database.CardDao
import com.dio.businesscard.data.local.LocalDataSourceImpl
import com.dio.businesscard.data.local.database.AppDatabase.Companion.getDatabase
import com.dio.businesscard.data.repository.CardRepositoryImpl
import com.dio.businesscard.domain.repository.CardRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase = getDatabase(context)

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase): CardDao = database.cardDao()

    @Provides
    fun provideLocalDataSource(dao: CardDao): LocalDataSource = LocalDataSourceImpl(dao)

    @Singleton
    @Provides
    fun bindNewsRepository(localDataSource: LocalDataSource): CardRepository = CardRepositoryImpl(localDataSource)

}