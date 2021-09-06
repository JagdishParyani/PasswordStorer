package com.example.passwordstorer.room.repository.categories

import com.example.passwordstorer.room.entity.CategoryEntity

interface CategoriesRepo {

    suspend fun insertCategory(category: CategoryEntity): Long

    suspend fun insertAllCategories(categoryList: List<CategoryEntity>): List<Long>

    suspend fun getAllCategories() : List<CategoryEntity>
}