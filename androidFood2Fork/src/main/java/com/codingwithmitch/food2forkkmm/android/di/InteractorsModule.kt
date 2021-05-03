package com.codingwithmitch.food2forkkmm.android.di

import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDtoMapper
import com.codingwithmitch.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.codingwithmitch.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
        )
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeService: RecipeService
    ): GetRecipe {
        return GetRecipe(
            recipeService = recipeService,
        )
    }
}







