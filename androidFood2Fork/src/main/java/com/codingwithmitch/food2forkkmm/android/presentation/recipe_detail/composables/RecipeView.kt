package com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkkmm.android.presentation.components.RecipeImage
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil

@ExperimentalStdlibApi
@Composable
fun RecipeView(
    recipe: Recipe,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            RecipeImage(
                url = recipe.featuredImage,
                contentDescription = recipe.title
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ){
                    Text(
                        text = recipe.title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start)
                        ,
                        style = MaterialTheme.typography.h3
                    )
                    val rank = recipe.rating.toString()
                    Text(
                        text = rank,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically)
                        ,
                        style = MaterialTheme.typography.h5
                    )
                }
                val datetimeUtil = remember{DatetimeUtil()}
                Text(
                    text = "Updated ${datetimeUtil.humanizeDatetime(recipe.dateUpdated)} by ${recipe.publisher}"
                    ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                    ,
                    style = MaterialTheme.typography.caption
                )
                for(ingredient in recipe.ingredients){
                    Text(
                        text = ingredient,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                        ,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}