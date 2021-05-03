package com.codingwithmitch.food2forkkmm.interactors.recipe_list

import com.codingwithmitch.food2forkkmm.datasource.Repository
import com.codingwithmitch.food2forkkmm.domain.model.Recipe

suspend fun Repository.searchRecipes(
    page: Int,
    query: String,
): List<Recipe> {
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
    return listData
}




