package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list

import com.codingwithmitch.food2forkkmm.interactors.recipe_list.searchRecipes
import com.codingwithmitch.food2forkkmm.util.Logger
import com.codingwithmitch.food2forkkmm.viewmodel.StateReducers

suspend fun StateReducers.newRecipeSearch(
    query: String,
){
    val listData = dataRepository.searchRecipes(page = 1, query = query)
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = false,
            query = query,
            recipes = listData
        )
    }
}

suspend fun StateReducers.nextPageRecipeSearch(){
    val logger = Logger("StateReducers.nextPageRecipeSearch")
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = true,
        )
    } // show loading while we wait for response use case to finish
    val currentState = stateManager.getScreenState(RecipeListState::class) as RecipeListState
    val listData = dataRepository.searchRecipes(page = currentState.page + 1, query = currentState.query)
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = false,
            recipes = listData
        )
    }
}

suspend fun StateReducers.selectCategory(category: FoodCategory){
    stateManager.updateScreen(RecipeListState::class){
        it.copy(selectedCategory = category)
    }
}







