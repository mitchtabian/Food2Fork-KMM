package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list

import com.codingwithmitch.food2forkkmm.interactors.recipe_list.searchRecipes
import com.codingwithmitch.food2forkkmm.util.BuildConfig
import com.codingwithmitch.food2forkkmm.viewmodel.StateReducers
import kotlinx.coroutines.delay

/**
 * If app is launching for first time
 */
suspend fun StateReducers.loadRecipes(){
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = true,
        )
    } // show loading while we wait for response use case to finish

    // delay so we can see the shimmer
    if(BuildConfig().isDebug()){
        delay(1000)
    }
    val currentState = stateManager.getScreenState(RecipeListState::class) as RecipeListState
    val listData = dataRepository.searchRecipes(page = currentState.page, query = currentState.query)
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = false,
            recipes = listData
        )
    }
}

suspend fun StateReducers.newRecipeSearch(){
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = true,
            recipes = listOf(), // set to empty list so it resets to top
        )
    } // show loading while we wait for response use case to finish

    // delay so we can see the shimmer
    if(BuildConfig().isDebug()){
        delay(1000)
    }

    val currentState = stateManager.getScreenState(RecipeListState::class) as RecipeListState
    val listData = dataRepository.searchRecipes(page = 1, query = currentState.query)
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = false,
            page = 1,
            recipes = listData
        )
    }
}

suspend fun StateReducers.nextPageRecipeSearch(){
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = true,
        )
    } // show loading while we wait for response use case to finish
    val currentState = stateManager.getScreenState(RecipeListState::class) as RecipeListState
    val newPage = currentState.page + 1
    val listData = dataRepository.searchRecipes(page = newPage, query = currentState.query)
    stateManager.updateScreen(RecipeListState::class){
        it.copy(
            isLoading = false,
            recipes = listData,
            page = newPage
        )
    }
}

suspend fun StateReducers.selectCategory(category: FoodCategory){
    stateManager.updateScreen(RecipeListState::class){
        it.copy(selectedCategory = category)
    }
}

suspend fun StateReducers.updateQuery(query: String){
    stateManager.updateScreen(RecipeListState::class){
        it.copy(query = query)
    }
}

suspend fun StateReducers.updateSelectedCategory(category: FoodCategory){
    stateManager.updateScreen(RecipeListState::class){
        it.copy(selectedCategory = category)
    }
}






