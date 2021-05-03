package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_detail

import com.codingwithmitch.food2forkkmm.interactors.recipe_detail.getRecipe
import com.codingwithmitch.food2forkkmm.util.BuildConfig
import com.codingwithmitch.food2forkkmm.viewmodel.StateReducers
import kotlinx.coroutines.delay

suspend fun StateReducers.getRecipeFromCache(recipeId: Int,){
    stateManager.updateScreen(RecipeDetailState::class){
        it.copy(
            isLoading = true,
        )
    } // show loading while we wait for response use case to finish

    // delay so we can see the shimmer
    if(BuildConfig().isDebug()){
        delay(1000)
    }

    val recipe = dataRepository.getRecipe(recipeId = recipeId)
    stateManager.updateScreen(RecipeDetailState::class){
        it.copy(
            isLoading = false,
            recipe = recipe
        )
    }
}