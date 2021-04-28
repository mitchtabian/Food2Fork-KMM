package com.codingwithmitch.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.codingwithmitch.food2forkkmm.android.presentation.navigation.Screen
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.RecipeListScreen
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.RecipeListViewModel
import com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.RecipeListViewState
import com.codingwithmitch.food2forkkmm.di.AppComponent
import com.codingwithmitch.food2forkkmm.domain.model.Recipe

private val RecipeListViewStateSaver = Saver<RecipeListViewState, RecipeListViewState>(
    save = { it },
    restore = {
        RecipeListViewState(
            page = it.page,
            query = it.query
        )
    }
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
                composable(route = Screen.RecipeList.route) { navBackStackEntry ->
                    val coroutineScope = rememberCoroutineScope() // If I rotate while a job is active, will this kill it?
                    val recipes: MutableState<List<Recipe>> = remember{ mutableStateOf(listOf())}
                    val page: MutableState<Int> = remember{ mutableStateOf(1)}
                    val query: MutableState<String> = remember{ mutableStateOf("")}
                    val viewModel = remember {
                        RecipeListViewModel(
                            recipeService = AppComponent().recipeService,
                            scope = coroutineScope,
                            onAppendRecipes = {
                                val curr = ArrayList(recipes.value)
                                curr.addAll(it)
                                recipes.value = curr
                            }
                        )
                    }
                    RecipeListScreen(
                        onNavigateToRecipeDetailScreen = navController::navigate,
                        recipes = recipes.value,
                        onNextPage = {
                            page.value = page.value + 1
                            viewModel.searchRecipes(page = page.value, query = query.value)
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
//                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
