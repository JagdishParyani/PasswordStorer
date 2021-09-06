package com.example.passwordstorer.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryEntity")
data class CategoryEntity(
    @PrimaryKey val categoryType: Int,
    val categoryName: String
)