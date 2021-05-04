package com.codingwithmitch.food2forkkmm.android.di

import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeDatabase
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDtoMapper
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil
import com.codingwithmitch.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.codingwithmitch.food2forkkmm.interactors.recipe_list.SearchRecipes
import com.example.kmmplayground.shared.datasource.cache.model.RecipeEntityMapper
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
        recipeService: RecipeService,
        recipeDatabase: RecipeDatabase,
        recipeEntityMapper: RecipeEntityMapper,
        datetimeUtil: DatetimeUtil,
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            recipeDatabase = recipeDatabase,
            recipeEntityMapper = recipeEntityMapper,
            dateUtil = datetimeUtil,
        )
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeDatabase: RecipeDatabase,
        entityMapper: RecipeEntityMapper,
        dateUtil: DatetimeUtil,
    ): GetRecipe {
        return GetRecipe(
            recipeDatabase = recipeDatabase,
            recipeEntityMapper = entityMapper,
            dateUtil = dateUtil
        )
    }
}







