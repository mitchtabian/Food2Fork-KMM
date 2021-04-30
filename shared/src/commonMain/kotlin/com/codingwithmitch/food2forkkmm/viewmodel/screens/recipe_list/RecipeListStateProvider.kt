package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list

import com.codingwithmitch.food2forkkmm.viewmodel.StateProviders

fun StateProviders.getRecipeListState(): RecipeListState {
    // the state gets initialized with "initState":
    //      ONLY WHEN this function is called for the first time
    // after initialization, the "callOnInit" code gets called
    return stateManager.getScreen(
        initState = { RecipeListState(isLoading = true) },
        callOnInit = { events.loadRecipes() },

    )
}