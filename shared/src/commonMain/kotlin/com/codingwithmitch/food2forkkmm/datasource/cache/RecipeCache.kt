package com.codingwithmitch.food2forkkmm.datasource.cache

import com.codingwithmitch.food2forkkmm.domain.model.Recipe

interface RecipeCache {

    fun insert(recipe: Recipe)

    fun insert(recipes: List<Recipe>)

    fun search(query: String, page: Int): List<Recipe>

    fun getAll(page: Int): List<Recipe>

    fun get(recipeId: Int): Recipe?
}