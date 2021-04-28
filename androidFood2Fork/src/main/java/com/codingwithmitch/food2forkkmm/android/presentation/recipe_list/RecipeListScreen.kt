package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkkmm.android.presentation.navigation.Screen

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
) {
    LazyColumn{
        itemsIndexed(items = viewModel.recipes.value){ index, recipe ->
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = { onNavigateToRecipeDetailScreen("${Screen.RecipeDetail.route}/${recipe.id}")})
            {
                Text(recipe.title)
            }
        }
    }
}