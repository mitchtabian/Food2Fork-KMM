package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.GenericMessageInfoQueueUtil
import com.codingwithmitch.food2forkkmm.domain.util.Queue
import com.codingwithmitch.food2forkkmm.interactors.recipe_list.SearchRecipes
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.FoodCategory
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.codingwithmitch.food2forkkmm.presentation.recipe_list.RecipeListState
import com.codingwithmitch.shared.domain.util.UIComponentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val searchRecipes: SearchRecipes,
): ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        viewModelScope.launch {
            loadRecipes()
        }
    }

    fun onTriggerEvent(event: RecipeListEvents){
        viewModelScope.launch {
            when (event){
                RecipeListEvents.LoadRecipes -> {
                    loadRecipes()
                }
                RecipeListEvents.NewSearch -> {
                    newSearch()
                }
                RecipeListEvents.NextPage -> {
                    nextPage()
                }
                is RecipeListEvents.OnSelectCategory -> {
                    onSelectCategory(event.category)
                }
                is RecipeListEvents.OnUpdateQuery -> {
                    state.value = state.value.copy(query =  event.query)
                }
                else -> {
                    val messageInfoBuilder = GenericMessageInfo.Builder()
                        .id(UUID.randomUUID().toString())
                        .title("Invalid Event")
                        .uiComponentType(UIComponentType.Dialog)
                        .description("Something went wrong.")
                    appendToMessageQueue(messageInfo = messageInfoBuilder)
                }
            }
        }
    }

    /**
     *  Called when a new FoodCategory chip is selected
     */
    private fun onSelectCategory(category: FoodCategory){
        state.value = state.value.copy(selectedCategory = category, query =  category.value)
        newSearch()
    }

    /**
     * Get the next page of recipes
     */
    private fun nextPage(){
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch(){
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(page = state.value.page, query = state.value.query).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>){
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder){
        if(!GenericMessageInfoQueueUtil()
                .doesMessageAlreadyExistInQueue(queue = state.value.queue,messageInfo = messageInfo.build())){
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }

    private fun updateQueue(queue: Queue<GenericMessageInfo>){
        state.value = state.value.copy(queue = Queue(mutableListOf())) // reset queue
        state.value = state.value.copy(queue = queue)
    }
}





