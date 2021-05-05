package com.codingwithmitch.food2forkkmm.presentation.recipe_list

import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.Queue

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf(),
    val selectedCategory: FoodCategory? = null,
    val foodCategories: List<FoodCategory> = FoodCategoryUtil().getAllFoodCategories(),
    val bottomRecipe: Recipe? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
    val isQueryInProgress: Boolean = false, // Is a query currently in progress? This will prevent duplicate queries.
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
)  {
    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        recipes = listOf(),
        selectedCategory = null,
        bottomRecipe = null,
        isQueryInProgress = false,
        queue = Queue(mutableListOf()),
    )

    companion object{
        const val RECIPE_PAGINATION_PAGE_SIZE = 30
    }
}