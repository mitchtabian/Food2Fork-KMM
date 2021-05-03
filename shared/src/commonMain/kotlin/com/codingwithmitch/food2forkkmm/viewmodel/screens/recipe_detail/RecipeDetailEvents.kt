package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_detail

import com.codingwithmitch.food2forkkmm.viewmodel.Events
import com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list.loadRecipes

/********** INTERNAL event function, called by the StateProvider's callOnInit **********/
internal fun Events.getRecipe(recipeId: Int) = screenCoroutine(RecipeDetailState::class){
    stateReducers.getRecipeFromCache(recipeId = recipeId)
}

/********** PUBLIC event functions, called directly by the UI layer **********/
