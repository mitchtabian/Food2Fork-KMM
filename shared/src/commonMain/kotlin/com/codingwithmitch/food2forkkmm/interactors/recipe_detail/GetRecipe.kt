package com.codingwithmitch.food2forkkmm.interactors.recipe_detail

import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeCache
import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.*
import com.codingwithmitch.food2forkkmm.util.BuildConfig
import com.codingwithmitch.shared.domain.util.UIComponentType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe (
    private val recipeCache: RecipeCache,
){

    fun execute(
        recipeId: Int,
    ): CommonFlow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())

            // just to show loading, cache is fast
            // Note: iOS loads the DetailView ahead of time so delaying here for iOS is pointless
            if(BuildConfig().isDebug() && BuildConfig().isAndroid()){
                delay(500)
            }

            // Force error for testing
            if(recipeId == 1){
                throw Exception("Invalid Recipe Id")
            }

            val recipe =  recipeCache.get(recipeId)

            emit(DataState.data(message = null, data = recipe))

        }catch (e: Exception){
            emit(DataState.error<Recipe>(
                message = GenericMessageInfo.Builder()
                    .id("GetRecipe.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message?: "Unknown Error")
            ))
        }
    }.asCommonFlow()


}







