package com.codingwithmitch.food2forkkmm.android.di

import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeCache
import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeDatabase
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil
import com.codingwithmitch.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.codingwithmitch.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeCache: RecipeCache,
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            recipeCache = recipeCache
        )
    }

    @InternalAPI
    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache,
    ): GetRecipe {
        return GetRecipe(
            recipeCache = recipeCache
        )
    }
}







