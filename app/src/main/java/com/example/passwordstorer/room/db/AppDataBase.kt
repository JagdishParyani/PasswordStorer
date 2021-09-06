package com.example.passwordstorer.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.passwordstorer.room.dao.CategoryDao
import com.example.passwordstorer.room.dao.PinDao
import com.example.passwordstorer.room.entity.CategoryEntity
import com.example.passwordstorer.room.entity.PinEntity

@Database(entities = [PinEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getPinDao(): PinDao

    abstract fun getCategoryDao(): CategoryDao

}