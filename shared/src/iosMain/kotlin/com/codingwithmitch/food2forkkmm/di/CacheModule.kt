package com.codingwithmitch.food2forkkmm.di

import com.codingwithmitch.food2forkkmm.datasource.cache.DriverFactory
import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeDatabase
import com.codingwithmitch.food2forkkmm.datasource.cache.RecipeDatabaseFactory

class CacheModule {

    private val driverFactory: DriverFactory by lazy {DriverFactory()}
    val entityMapper: RecipeEntityMapper by lazy{ RecipeEntityMapper() }
    val recipeDatabase: RecipeDatabase by lazy{
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }


}








