package com.codingwithmitch.food2forkkmm.di

import com.codingwithmitch.food2forkkmm.datasource.network.BASE_URL
import com.codingwithmitch.food2forkkmm.datasource.network.KtorClientFactory
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeServiceImpl
import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDtoMapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

class AppComponent: KoinComponent {
    val recipeService by inject<RecipeService>()
}

val recipeServiceModule = module {
    single{ RecipeServiceImpl(
        recipeDtoMapper = RecipeDtoMapper(),
        httpClient = KtorClientFactory().build(),
        baseUrl = BASE_URL
    ) as RecipeService}

}








