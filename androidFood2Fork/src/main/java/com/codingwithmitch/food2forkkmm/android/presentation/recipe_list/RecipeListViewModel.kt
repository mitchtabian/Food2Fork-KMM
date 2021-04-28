package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val recipeService: RecipeService,
    private val scope: CoroutineScope
) {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    init {
        scope.launch {
            val recipeList = recipeService.search(page = 1, query = "")
            recipes.value = recipeList
        }
    }
}








