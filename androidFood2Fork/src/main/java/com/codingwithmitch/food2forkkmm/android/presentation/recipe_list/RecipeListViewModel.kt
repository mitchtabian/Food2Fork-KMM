package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class RecipeListViewState(
    val page: Int = 1,
    val query: String = "",
):  Parcelable {

    constructor(parcel: Parcel) : this(
        page = parcel.readInt(),
        query = parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(page)
        parcel.writeString(query)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeListViewState> {

        override fun createFromParcel(parcel: Parcel): RecipeListViewState {
            return RecipeListViewState(parcel)
        }

        override fun newArray(size: Int): Array<RecipeListViewState?> {
            return arrayOfNulls(size)
        }
    }

}

class RecipeListViewModel(
    page: Int = 1, // initial value
    query: String = "", // initial value
    private val recipeService: RecipeService, // could be a use case
    private val onAppendRecipes: (List<Recipe>) -> Unit,
){

    init {
        for(p in 1..page){ // restore after rotation or process death
            searchRecipes(p, query)
        }
    }

    suspend fun searchRecipes(page: Int, query: String){
        val recipeList = recipeService.search(page = page, query = query)
        onAppendRecipes.invoke(ArrayList(recipeList))
    }
}








