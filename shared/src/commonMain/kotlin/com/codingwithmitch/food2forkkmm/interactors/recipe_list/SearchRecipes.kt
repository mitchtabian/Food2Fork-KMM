package com.codingwithmitch.food2forkkmm.interactors.recipe_list

import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeCache
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow  {
        emit(DataState.loading())
        try{
            val recipes = recipeService.search(
                page = page,
                query = query,
            )

            // delay 500ms so we can see loading
            delay(500)

            // force error for testing
            if (query == "error") {
                throw Exception("Forcing an error... Search FAILED!")
            }

            // insert into cache
            recipeCache.insert(recipes)

            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(
                    query = query,
                    page = page,
                )
            }
            // emit List<Recipe> from cache
            emit(DataState.data<List<Recipe>>(message = null, data = cacheResult))
        }catch (e: Exception){
            emit(DataState.error<List<Recipe>>(message = e.message?: "Unknown Error"))
        }
    }
}









