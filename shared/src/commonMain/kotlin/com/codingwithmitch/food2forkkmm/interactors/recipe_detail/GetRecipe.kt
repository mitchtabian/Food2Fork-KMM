package com.codingwithmitch.food2forkkmm.interactors.recipe_detail

import com.codingwithmitch.food2forkkmm.datasource.Repository
import com.codingwithmitch.food2forkkmm.domain.model.Recipe

suspend fun Repository.getRecipe(
    recipeId: Int,
): Recipe {
    // TODO("This should get from cache not network")
    return  recipeService.get(id = recipeId)
}