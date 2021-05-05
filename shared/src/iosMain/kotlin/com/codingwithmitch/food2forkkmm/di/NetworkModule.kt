package com.codingwithmitch.food2forkkmm.di

import com.codingwithmitch.food2forkkmm.datasource.cache.DriverFactory
import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeDatabase
import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeDatabaseFactory
import com.codingwithmitch.food2forkkmm.datasource.network.KtorClientFactory
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeServiceImpl
import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDtoMapper
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil
import com.example.kmmplayground.shared.datasource.cache.model.RecipeEntityMapper

class NetworkModule {

    val dtoMapper: RecipeDtoMapper by lazy{RecipeDtoMapper()}
    val recipeService: RecipeService by lazy {
        RecipeServiceImpl(
            recipeDtoMapper = dtoMapper,
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL
        )
    }

}








