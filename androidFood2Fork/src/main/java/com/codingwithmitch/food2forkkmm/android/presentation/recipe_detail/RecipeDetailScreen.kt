package com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkcompose.presentation.theme.AppTheme
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.components.RecipeView
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components.RECIPE_IMAGE_HEIGHT
import com.codingwithmitch.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.codingwithmitch.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalStdlibApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit,
){
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
           onTriggerEvent(RecipeDetailEvents.OnRemoveHeadMessageFromQueue)
        }
    ){
        if(state.recipe == null && state.isLoading){
            LoadingRecipeShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
        }
        else if(state.recipe == null){
            Text(
                modifier = Modifier.padding(16.dp),
                text = "We were unable to retrieve the details for this recipe.\nTry resetting the app.",
                style = MaterialTheme.typography.body1
            )
        }
        else{
            RecipeView(recipe = state.recipe!!)
        }
    }
}









