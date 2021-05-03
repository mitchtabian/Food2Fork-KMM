package com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.codingwithmitch.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import com.codingwithmitch.food2forkkmm.util.Logger
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
): ViewModel() {

    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            viewModelScope.launch {
                getRecipe(recipeId = recipeId)
            }
        }
    }

    private suspend fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipeDetailState ->
                state.value = state.value.copy(recipe = recipeDetailState.recipe)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo){
        val queue = state.value.queue
        queue.add(messageInfo)
        state.value = state.value.copy(queue = queue)
    }
}










