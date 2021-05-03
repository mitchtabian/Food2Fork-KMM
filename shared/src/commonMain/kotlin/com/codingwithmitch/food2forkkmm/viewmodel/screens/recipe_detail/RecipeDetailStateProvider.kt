package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_detail

import com.codingwithmitch.food2forkkmm.viewmodel.StateProviders

fun StateProviders.getRecipeDetailState(recipeId: Int): RecipeDetailState {
    // the state gets initialized with "initState":
    //      ONLY WHEN this function is called for the first time
    // after initialization, the "callOnInit" code gets called
    return stateManager.getScreen(
        initState = { RecipeDetailState(isLoading = true) },
        callOnInit = { events.getRecipe(recipeId = recipeId) },
        )
}