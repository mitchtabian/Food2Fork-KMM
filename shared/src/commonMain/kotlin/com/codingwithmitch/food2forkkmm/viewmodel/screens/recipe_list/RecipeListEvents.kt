package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list

import com.codingwithmitch.food2forkkmm.viewmodel.Events

/********** INTERNAL event function, called by the StateProvider's callOnInit **********/

// Show first page of recipes with query = ""
internal fun Events.loadRecipes() = screenCoroutine(RecipeListState::class){
    stateReducers.newRecipeSearch(query = "")
}

/********** PUBLIC event functions, called directly by the UI layer **********/

fun Events.newSearch(query: String) = screenCoroutine(RecipeListState::class){
    stateReducers.newRecipeSearch(query = query)
}

fun Events.nextPage() = screenCoroutine(RecipeListState::class){
    stateReducers.nextPageRecipeSearch()
}

