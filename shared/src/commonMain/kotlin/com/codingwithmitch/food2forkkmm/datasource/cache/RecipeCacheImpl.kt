package com.codingwithmitch.food2forkkmm.datasource.cache

import com.codingwithmitch.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil

class RecipeCacheImpl(
    val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil,
): RecipeCache {

    private var queries: RecipeDbQueries = recipeDatabase.recipeDbQueries

    override fun insert(recipe: Recipe) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            ingredients = , // TODO("Convert String to List<String>")
            date_updated = datetimeUtil.toEpochMilliseconds(recipe.dateUpdated),
            date_added = datetimeUtil.toEpochMilliseconds(recipe.dateAdded),
        )
    }

    override fun insert(recipes: List<Recipe>) {
        for(recipe in recipes){
            insert(recipe)
        }
    }

    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query,
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = (page * RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList() // TODO("convert List<Recipe_Entity> to List<Recipe>")
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.getAllRecipes(
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = (page * RECIPE_PAGINATION_PAGE_SIZE).toLong()
        ).executeAsList() // TODO("convert List<Recipe_Entity> to List<Recipe>")
    }

    override fun get(recipeId: Int): Recipe? {
        return try {
            queries
                .getRecipeById(id = recipeId.toLong())
                .executeAsOne() // TODO("convert Recipe_Entity to Recipe")
        }catch (e: NullPointerException){
            null
        }
    }
}