package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkcompose.presentation.theme.AppTheme
import com.codingwithmitch.food2forkkmm.android.presentation.navigation.Screen
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.viewmodel.Events
import com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list.RecipeListState
import com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list.nextPage

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    events : Events,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(displayProgressBar = state.isLoading) {
        LazyColumn {
            stickyHeader { // for debugging
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                    ,
                    text = "${state.recipes.size}",
                    style = MaterialTheme.typography.h3
                )
            }
            itemsIndexed(items = state.recipes){ index, recipe ->
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            onClickRecipeListItem(recipe.id)
                        }
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
                        onClick = {
                            events.nextPage()
                        }
                    ) {
                        Text(
                            text = "GO NEXT"
                        )
                    }
                }
            }
        }
    }

}