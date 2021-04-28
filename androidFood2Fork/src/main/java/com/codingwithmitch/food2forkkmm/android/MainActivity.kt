package com.codingwithmitch.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.RecipeListViewModel
import com.codingwithmitch.food2forkkmm.datasource.network.BASE_URL
import com.codingwithmitch.food2forkkmm.datasource.network.KtorClientFactory
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeServiceImpl
import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDtoMapper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val coroutineScope = rememberCoroutineScope()
            val viewModel = remember{
                RecipeListViewModel(
                    recipeService = RecipeServiceImpl(
                        recipeDtoMapper = RecipeDtoMapper(),
                        httpClient = KtorClientFactory().build(),
                        baseUrl = BASE_URL
                    ),
                    scope = coroutineScope
                )
            }
            LazyColumn{
                itemsIndexed(items = viewModel.recipes.value){ index, recipe ->
                    Text(recipe.title)
                }
            }
        }
    }
}
