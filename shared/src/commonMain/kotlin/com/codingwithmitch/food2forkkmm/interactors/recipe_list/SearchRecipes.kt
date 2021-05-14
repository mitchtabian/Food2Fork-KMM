package com.codingwithmitch.food2forkkmm.interactors.recipe_list

import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<List<Recipe>> = flow  {
        // how can we emit loading?

        // emit recipes
        try{
            val recipes = recipeService.search(
                page = page,
                query = query,
            )
            emit(recipes)
        }catch (e: Exception){
            // how can we emit an error?
        }
    }
}









