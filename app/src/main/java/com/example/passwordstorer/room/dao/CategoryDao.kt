package com.example.passwordstorer.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.passwordstorer.room.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(category: List<CategoryEntity>): List<Long>

    @Query("SELECT * FROM CategoryEntity")
    suspend fun getAllCategories(): List<CategoryEntity>

}