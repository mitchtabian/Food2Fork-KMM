package com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.domain.util.GenericMessageInfoQueueUtil
import com.codingwithmitch.food2forkkmm.domain.util.Queue
import com.codingwithmitch.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.codingwithmitch.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId = recipeId).collectCommon(viewModelScope) { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipe ->
                state.value = state.value.copy(recipe = recipe)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }
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










