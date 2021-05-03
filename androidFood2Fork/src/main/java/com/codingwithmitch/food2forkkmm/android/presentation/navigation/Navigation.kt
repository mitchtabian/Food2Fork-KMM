package com.codingwithmitch.food2forkkmm.android.presentation.navigation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.codingwithmitch.food2forkkmm.viewmodel.DKMPViewModel
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.RecipeListScreen
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.logger
import com.codingwithmitch.food2forkkmm.util.printLogD
import com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_detail.getRecipeDetailState
import com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list.getRecipeListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.core.component.getScopeId

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation(model: DKMPViewModel){

    val appState by model.stateFlow.collectAsState()
    logger.log("recomposition Index: "+appState.recompositionIndex.toString())
    val stateProviders = appState.getStateProviders(model)
    val events = model.events
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            RecipeListScreen(
                state = stateProviders.getRecipeListState(),
                events = events,
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
            RecipeDetailScreen(
                state = stateProviders.getRecipeDetailState(
                    recipeId = navBackStackEntry.arguments?.getInt("recipeId")?: -1, // -1 = error
                ),
                events = events
            )
        }
    }
}










