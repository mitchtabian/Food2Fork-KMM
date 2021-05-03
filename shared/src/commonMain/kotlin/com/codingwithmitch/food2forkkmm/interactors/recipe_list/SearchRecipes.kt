package com.codingwithmitch.food2forkkmm.interactors.recipe_list

import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.DataState
import com.codingwithmitch.food2forkkmm.util.BuildConfig
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.RecipeListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService
){
    suspend fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<RecipeListState>> = flow{
        emit(DataState.loading())
        if(BuildConfig().isDebug()){
            delay(700)
        }
        // TODO("add caching and error handling")
        val listData: ArrayList<Recipe> = ArrayList()
        for(p in 1..page){ // won't need to do this looping once I have caching
            listData.addAll(
                recipeService.search(
                    page = p,
                    query = query,
                )
            )
        }
        emit(
            DataState.data(
                data = RecipeListState(
                    recipes = listData
                )
            )
        )
    }
}




