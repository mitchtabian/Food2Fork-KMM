package com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_detail

import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.viewmodel.ScreenState

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
): ScreenState