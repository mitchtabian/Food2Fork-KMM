package com.codingwithmitch.food2forkkmm.android.presentation.navigation

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.RecipeDetailViewModel
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.RecipeListScreen
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.RecipeListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation(activity: Activity){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
            RecipeListScreen(
                state = viewModel.state.value,
                onTriggerEvent = viewModel::onTriggerEvent,
                onClickRecipeListItem = { recipeId ->
                    navController.navigate("${Screen.RecipeDetail.route}/$recipeId")
                }
            )
        }
        composable(
            route = Screen.RecipeDetail.route + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            if (navBackStackEntry.arguments?.getInt("recipeId") == null){
                // TODO("Show error dialog or something")
                Text("Invalid Recipe")
            }
            else{
                val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                val viewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel", factory)
                RecipeDetailScreen(
                    state = viewModel.state.value
                )
            }
        }
    }
}










