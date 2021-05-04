package com.codingwithmitch.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.food2forkkmm.android.presentation.util.doesMessageAlreadyExistInQueue
import com.codingwithmitch.food2forkkmm.domain.model.GenericMessageInfo
import com.codingwithmitch.food2forkkmm.domain.model.PositiveAction
import com.codingwithmitch.food2forkkmm.domain.util.Queue
import com.codingwithmitch.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.codingwithmitch.food2forkkmm.presentation.recipe_detail.RecipeDetailState
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
        try {
            savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
                viewModelScope.launch {
                    getRecipe(recipeId = recipeId)
                }
            }
        }catch (e: Exception){
            // will throw exception if arg is not there for whatever reason.
            // we don't need to do anything because it will already show a composable saying "Unable to get the details of this recipe..."
        }
    }

    private fun getRecipe(recipeId: Int){
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipe ->
                state.value = state.value.copy(recipe = recipe)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder){
        if(!state.value.queue.doesMessageAlreadyExistInQueue(messageInfo = messageInfo.build())){
            if(messageInfo.onDismiss == null){ // Users must be able to dismiss the message if it's a dialog
                messageInfo
                    .onDismiss {
                        // remove from queue
                        val queue = state.value.queue
                        queue.remove() // if this message is visible it means it's at the head
                        updateQueue(queue)
                    }
                    .positive(
                        PositiveAction(
                            positiveBtnTxt = "OK",
                            onPositiveAction = {
                                val queue = state.value.queue
                                queue.remove()
                                updateQueue(queue)
                            }
                        )
                    )
            }
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










