package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkkmm.android.presentation.navigation.Screen
import com.codingwithmitch.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeListScreen(
    recipes: List<Recipe>,
    onNextPage: () -> Unit,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
) {
    LazyColumn {
        itemsIndexed(items = recipes){ index, recipe ->
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { onNavigateToRecipeDetailScreen("${Screen.RecipeDetail.route}/${recipe.id}") }
                ,
                text = recipe.title
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            )
                {
                Button(
                    modifier = Modifier
                        .padding(16.dp),
                    onClick = onNextPage
                ) {
                    Text(
                        text = "GO NEXT"
                    )
                }
            }
        }
    }
}