package com.example.passwordstorer.room.repository.categories

import com.example.passwordstorer.room.dao.CategoryDao
import com.example.passwordstorer.room.entity.CategoryEntity
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor (private val categoryDao: CategoryDao) : CategoriesRepo {

    override suspend fun insertCategory(category: CategoryEntity): Long {
        return categoryDao.insertCategory(category)
    }

    override suspend fun insertAllCategories(categoryList: List<CategoryEntity>): List<Long> {
        return categoryDao.insertAllCategories(categoryList)
    }

    override suspend fun getAllCategories(): List<CategoryEntity> {
        return categoryDao.getAllCategories()
    }
}