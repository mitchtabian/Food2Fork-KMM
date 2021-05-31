package com.codingwithmitch.food2forkkmm.di

import com.codingwithmitch.food2forkkmm.datasource.cache.*
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil

class CacheModule {

    private val driverFactory: DriverFactory by lazy {DriverFactory()}
    val recipeDatabase: RecipeDatabase by lazy{
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }
    val recipeCache: RecipeCache by lazy {
        RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil = DatetimeUtil()
        )
    }


}