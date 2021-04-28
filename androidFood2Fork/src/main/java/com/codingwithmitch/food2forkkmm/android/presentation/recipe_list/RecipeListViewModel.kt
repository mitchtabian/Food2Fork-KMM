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
    private val recipeService: RecipeService,
    private val scope: CoroutineScope,
    private val onAppendRecipes: (List<Recipe>) -> Unit,
){

    init {
        searchRecipes(1, "")
    }

    fun searchRecipes(page: Int, query: String){
        scope.launch {
            val recipeList = recipeService.search(page = page, query = query)
            Log.d("gfgfd", "searchRecipes: ${recipeList.size}")
            onAppendRecipes.invoke(ArrayList(recipeList))
        }
    }
}








