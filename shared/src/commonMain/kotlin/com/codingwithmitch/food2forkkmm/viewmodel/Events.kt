package com.codingwithmitch.food2forkkmm.viewmodel

import com.codingwithmitch.food2forkkmm.util.Logger
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class Events (stateReducers: StateReducers) {

    internal val stateReducers by lazy { stateReducers }

    private val logger = Logger("Events")

    // we run each event function on a Dispatchers.Main coroutine, scoped on the specific screen
    fun screenCoroutine (stateClass : KClass<out ScreenState>, block: suspend () -> Unit) {
        val screenScope = stateReducers.stateManager.getScreenScope(stateClass)
        logger.log(stateClass.simpleName+" Event is called")
        screenScope?.launch {
            block()
        }
    }

}






