package com.example.kmmplayground.shared.datasource.cache.model

data class RecipeEntity(
    val id: Long,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Long,
    val sourceUrl: String,
    val ingredients: String,
    val dateAdded: Double,
    val dateUpdated: Double,
)
