package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.interactors.recipe_list.SearchRecipes
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.FoodCategory
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val searchRecipes: SearchRecipes,
) : ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)
    }

    fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query, selectedCategory = null)
            }
            is RecipeListEvents.OnSelectCategory -> {
                onSelectCategory(event.category)
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }

    /**
     *  Called when a new FoodCategory chip is selected
     */
    private fun onSelectCategory(category: FoodCategory) {
        state.value = state.value.copy(selectedCategory = category, query = category.value)
        newSearch()
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    /**
     * Get the next page of recipes
     */
    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.message?.let { message ->
                handleError(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun handleError(errorMessage: String) {
        // TODO("Handle errors")
    }
}











