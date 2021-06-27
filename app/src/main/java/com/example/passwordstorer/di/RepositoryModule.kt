package com.example.passwordstorer.di

import com.example.passwordstorer.room.repository.AppDataBaseRepository
import com.example.passwordstorer.room.repository.AppDatabaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAppDataBaseRepository(appDatabaseRepositoryImpl: AppDatabaseRepositoryImpl): AppDataBaseRepository
}