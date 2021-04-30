package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list

import com.codingwithmitch.food2forkkmm.viewmodel.Events

/********** INTERNAL event function, called by the StateProvider's callOnInit **********/

/**
 * If app is launching for first time
 */
internal fun Events.loadRecipes() = screenCoroutine(RecipeListState::class){
    stateReducers.loadRecipes()
}

/********** PUBLIC event functions, called directly by the UI layer **********/

fun Events.executeNewSearch() = screenCoroutine(RecipeListState::class){
    stateReducers.newRecipeSearch()
}

fun Events.nextPage() = screenCoroutine(RecipeListState::class){
    stateReducers.nextPageRecipeSearch()
}

fun Events.onUpdateQuery(query: String) = screenCoroutine(RecipeListState::class){
    stateReducers.updateQuery(query)
}

fun Events.onSelectedCategoryChanged(category: FoodCategory) = screenCoroutine(RecipeListState::class){
    stateReducers.updateSelectedCategory(category)
    stateReducers.updateQuery(category.value)
}







