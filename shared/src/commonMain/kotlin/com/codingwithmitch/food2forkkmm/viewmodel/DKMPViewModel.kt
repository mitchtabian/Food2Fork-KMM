package com.codingwithmitch.food2forkkmm.viewmodel

import com.codingwithmitch.food2forkkmm.datasource.Repository
import com.codingwithmitch.food2forkkmm.util.Logger
import kotlinx.coroutines.flow.StateFlow

class DKMPViewModel(
    repository: Repository
) {
    companion object Factory {
        // factory methods are defined in the platform-specific shared code (androidMain and iosMain)
    }

    private val logger = Logger("DKMPViewModel")

    private val stateManager by lazy { StateManager() }
    private val stateReducers by lazy { StateReducers(stateManager, repository) }

    val events by lazy { Events(stateReducers) }
    internal val stateProviders by lazy { StateProviders(stateManager, events) }

    val stateFlow: StateFlow<AppState>
        get() = stateManager.mutableStateFlow

    fun onReEnterForeground() {
        // not called at app startup, but only when reentering the app after it was in background
        logger.log("onReEnterForeground: recomposition is triggered")
        stateManager.triggerRecomposition()
    }

    fun onEnterBackground() {
        logger.log("onEnterBackground: screen scopes are cancelled")
        stateManager.cancelScreenScopes()
    }

}











