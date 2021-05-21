package com.codingwithmitch.food2forkkmm.presentation.recipe_list

// TODO("We will be adding more events to this UI as the course progresses")
sealed class RecipeListEvents {

    object LoadRecipes: RecipeListEvents()

    object NextPage: RecipeListEvents()

}