package com.example.passwordstorer.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryModel(
    val categoryType: Int,
    val categoryName: String,
    val categoryImage: String
) : BaseModel()
