package com.example.passwordstorer.di

import com.example.passwordstorer.room.repository.categories.CategoriesRepo
import com.example.passwordstorer.room.repository.categories.CategoriesRepoImpl
import com.example.passwordstorer.room.repository.pin.PinDBRepo
import com.example.passwordstorer.room.repository.pin.PinDBRepoImpl
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
    abstract fun bindPinDBRepo(pinDBRepoImpl: PinDBRepoImpl): PinDBRepo

    @Singleton
    @Binds
    abstract fun bindCategoriesRepo(categoriesRepoImpl: CategoriesRepoImpl): CategoriesRepo
}