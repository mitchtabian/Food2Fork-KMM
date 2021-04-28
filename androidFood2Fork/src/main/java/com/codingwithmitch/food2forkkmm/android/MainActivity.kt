package com.codingwithmitch.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
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
//                    val viewModel = RecipeListViewModel(
//                        recipeService = RecipeServiceImpl(
//                            recipeDtoMapper = RecipeDtoMapper(),
//                            httpClient = KtorClientFactory().build(),
//                            baseUrl = BASE_URL
//                        ),
//                        scope = coroutineScope
//                    )
                    val viewState = rememberSaveable(
                        saver = RecipeListViewStateSaver,
                    ){
                        RecipeListViewState()
                    }
                    val viewModel = RecipeListViewModel(
                        recipeService = AppComponent().recipeService,
                        scope = coroutineScope,
//                        _viewState = viewState
                        viewState = viewState
                    )
                    RecipeListScreen(
                        onNavigateToRecipeDetailScreen = navController::navigate,
                        viewModel = viewModel,
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
