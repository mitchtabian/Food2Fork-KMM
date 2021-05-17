package com.codingwithmitch.food2forkkmm.interactors.recipe_list

import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeCache
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.*
import com.codingwithmitch.food2forkkmm.util.Logger
import com.codingwithmitch.shared.domain.util.UIComponentType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
){
    private val logger = Logger("SearchRecipes")

    fun execute(
        page: Int,
        query: String,
    ): CommonFlow<DataState<List<Recipe>>> = flow  {
        try{
            emit(DataState.loading())

            // just to show pagination, api is fast
            delay(500)

            // force error for testing
            if (query == "error") {
                throw Exception("Forcing an error... Search FAILED!")
            }

            val recipes = recipeService.search(
                page = page,
                query = query,
            )
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
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipes.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message?: "Unknown Error")
            ))
        }
    }.asCommonFlow()

}




