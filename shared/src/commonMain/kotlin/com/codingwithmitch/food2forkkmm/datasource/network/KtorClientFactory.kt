package com.codingwithmitch.food2forkkmm.datasource.network

import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDto
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}

fun RecipeDto.toRecipe(): Recipe{
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = datetimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = datetimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}