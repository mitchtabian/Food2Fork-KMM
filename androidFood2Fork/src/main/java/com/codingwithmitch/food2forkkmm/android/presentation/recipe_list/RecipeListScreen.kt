package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.codingwithmitch.food2forkcompose.presentation.theme.AppTheme
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components.RecipeList
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components.SearchAppBar
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
            onTriggerEvent(RecipeListEvents.OnRemoveHeadMessageFromQueue)
        }
    ) {
        val foodCategories = remember{FoodCategoryUtil().getAllFoodCategories()}
        Scaffold(
            topBar = {
                SearchAppBar(
                    query = state.query,
                    onQueryChanged = {
                        onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                     },
                    onExecuteSearch = {
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    },
                    categories = foodCategories,
                    selectedCategory = state.selectedCategory,
                    onSelectedCategoryChanged = {
                        onTriggerEvent(RecipeListEvents.OnSelectCategory(it))
                    },
                )
            },
        ) {
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                },
                onClickRecipeListItem = onClickRecipeListItem
            )
        }
    }

}