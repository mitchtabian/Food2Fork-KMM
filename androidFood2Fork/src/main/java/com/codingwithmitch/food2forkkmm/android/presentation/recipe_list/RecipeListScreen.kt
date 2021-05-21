package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components.GradientDemo
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components.RecipeList
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components.SearchAppBar
import com.codingwithmitch.food2forkkmm.android.presentation.theme.AppTheme
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.RecipeListState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onSelectRecipe: (Int) -> Unit,
){
    AppTheme(
        displayProgressBar = state.isLoading
    ) {
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
                onClickRecipeListItem = onSelectRecipe
            )
        }
    }
}





