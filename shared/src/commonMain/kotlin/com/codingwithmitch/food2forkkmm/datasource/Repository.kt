package com.codingwithmitch.food2forkkmm.datasource

import com.codingwithmitch.food2forkkmm.datasource.network.BASE_URL
import com.codingwithmitch.food2forkkmm.datasource.network.KtorClientFactory
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeServiceImpl
import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDtoMapper

class Repository() {

    internal val dtoMapper by lazy{RecipeDtoMapper()}

    internal val recipeService by lazy {
        RecipeServiceImpl(
            recipeDtoMapper = dtoMapper,
            httpClient = KtorClientFactory().build(),
            baseUrl = BASE_URL,
        )
    }


}






