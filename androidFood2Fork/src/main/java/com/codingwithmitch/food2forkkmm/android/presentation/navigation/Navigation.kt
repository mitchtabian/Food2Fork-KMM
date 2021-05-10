package com.codingwithmitch.food2forkkmm.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.RecipeListScreen
import java.util.*

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route) { navBackStackEntry ->
            RecipeListScreen(
                onSelectRecipe = { recipeId ->
                    navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
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
                recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
            )
        }
    }
}
