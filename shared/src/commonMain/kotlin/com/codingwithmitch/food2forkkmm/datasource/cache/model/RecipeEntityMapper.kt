package com.example.kmmplayground.shared.datasource.cache.model

import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil
import com.codingwithmitch.food2forkkmm.domain.util.DomainMapper


class RecipeEntityMapper: DomainMapper<RecipeEntity, Recipe> {
    
    private val dateUtil = DatetimeUtil()

    override fun mapToDomainModel(model: RecipeEntity): Recipe {
        return Recipe(
            id = model.id.toInt(),
            title = model.title,
            publisher = model.publisher,
            featuredImage = model.featuredImage,
            rating = model.rating.toInt(),
            sourceUrl = model.sourceUrl,
            ingredients = convertIngredientsToList(model.ingredients),
            dateAdded = dateUtil.toLocalDate(model.dateAdded),
            dateUpdated = dateUtil.toLocalDate(model.dateUpdated)
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeEntity {
        return RecipeEntity(
            id = domainModel.id.toLong(),
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating.toLong(),
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            ingredients = convertIngredientListToString(domainModel.ingredients),
            dateAdded = dateUtil.toEpochMilliseconds(domainModel.dateAdded),
            dateUpdated = dateUtil.toEpochMilliseconds(domainModel.dateUpdated),
        )
    }

    /**
     * "Carrot, potato, Chicken, ..."
     */
    fun convertIngredientListToString(ingredients: List<String>): String {
        val ingredientsString = StringBuilder()
        for(ingredient in ingredients){
            ingredientsString.append("$ingredient,")
        }
        return ingredientsString.toString()
    }

    fun convertIngredientsToList(ingredientsString: String?): List<String>{
        val list: ArrayList<String> = ArrayList()
        ingredientsString?.let {
            for(ingredient in it.split(",")){
                list.add(ingredient)
            }
        }
        return list
    }

    fun fromEntityList(initial: List<RecipeEntity>): List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Recipe>): List<RecipeEntity>{
        return initial.map { mapFromDomainModel(it) }
    }


}