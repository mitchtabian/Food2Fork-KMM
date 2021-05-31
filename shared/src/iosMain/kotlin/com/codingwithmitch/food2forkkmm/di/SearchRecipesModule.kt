package com.codingwithmitch.food2forkkmm.di

import com.codingwithmitch.food2forkkmm.interactors.recipe_list.SearchRecipes

class SearchRecipesModule(
    val networkModule: NetworkModule,
    val cacheModule: CacheModule,
) {

    val searchRecipes: SearchRecipes by lazy{
        SearchRecipes(
            recipeService = networkModule.recipeService,
            recipeCache = cacheModule.recipeCache
        )
    }

}