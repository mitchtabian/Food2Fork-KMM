package com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    recipeId: Int?,
){
    if(recipeId == null){
        Text("ERROR")
    }
    else{
        Column{
            Text("RecipeDetailScreen: $recipeId")
        }
    }
}