package com.example.passwordstorer.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.passwordstorer.room.dao.PinDao
import com.example.passwordstorer.room.entity.PinEntity

@Database(entities = [PinEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getPinDao(): PinDao
}