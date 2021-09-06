package com.example.passwordstorer.di

import android.content.Context
import androidx.room.Room
import com.example.passwordstorer.common.utils.Constants
import com.example.passwordstorer.room.dao.CategoryDao
import com.example.passwordstorer.room.dao.PinDao
import com.example.passwordstorer.room.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            Constants.APP_DB_FILE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providePinDao(appDataBase: AppDataBase): PinDao = appDataBase.getPinDao()

    @Singleton
    @Provides
    fun provideCategoryDao(appDataBase: AppDataBase): CategoryDao = appDataBase.getCategoryDao()
}