package com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components.RECIPE_IMAGE_HEIGHT
import com.codingwithmitch.food2forkkmm.presentation.recipe_detail.RecipeDetailState

@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
){
    if(state.recipe == null && state.isLoading){
        LoadingRecipeShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
    }
    else{
        Text("${state.recipe?.title}")
    }
}









