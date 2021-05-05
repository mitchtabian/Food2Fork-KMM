package com.codingwithmitch.food2forkkmm.di

import com.codingwithmitch.food2forkkmm.interactors.recipe_detail.GetRecipe

class GetRecipeModule(
    private val appModule: AppModule,
    private val cacheModule: CacheModule,
) {

    val getRecipe: GetRecipe by lazy{
        GetRecipe(
            recipeDatabase = cacheModule.recipeDatabase,
            recipeEntityMapper = cacheModule.entityMapper,
            dateUtil = appModule.dateUtil,
        )
    }
}






