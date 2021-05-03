package com.codingwithmitch.food2forkkmm.interactors.recipe_detail

import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.DataState
import com.codingwithmitch.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import com.codingwithmitch.food2forkkmm.util.BuildConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe (
    private val recipeService: RecipeService,
){
    suspend fun execute(
        recipeId: Int,
    ): Flow<DataState<RecipeDetailState>> = flow{
        // TODO("Add error handling and this should not be a network operation. Should be cache only.")
        emit(DataState.loading())
        if(BuildConfig().isDebug()){
            delay(700)
        }
        emit(
            DataState.data(
                data = RecipeDetailState(
                    recipe = recipeService.get(recipeId)
                )
            )
        )
    }
}







