package com.codingwithmitch.food2forkkmm.viewmodel

import com.codingwithmitch.food2forkkmm.util.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

class StateManager {

    private val logger = Logger("StateManager.getScreen")

    internal val mutableStateFlow = MutableStateFlow(AppState())

    val screenStatesMap : MutableMap<ScreenType,ScreenState> = mutableMapOf() // map of screen states currently in memory
    val screenScopesMap : MutableMap<ScreenType, CoroutineScope> = mutableMapOf() // map of coroutine scopes associated to current screen states

    // only called by the State Providers
    inline fun <reified T:ScreenState> getScreen(
        initState: () -> T,
        callOnInit: () -> Unit,
        reinitWhen: (T) -> Boolean = {false},
    ) : T {
        val logger = Logger("StateManager.getScreen")
        logger.log("getScreen: "+T::class.simpleName)
        val loggerText = T::class.simpleName+" StateProvider is called"
        val screenType = getScreenType(T::class)
        val currentState = screenStatesMap[screenType] as? T
        if (currentState == null || reinitWhen(currentState)) {
            logger.log(loggerText+" (INITIALIZED state)")
            initScreenScope(screenType)
            val initializedState = initState()
            screenStatesMap[screenType] = initializedState
            callOnInit()
            return initializedState
        }
        if (!isScreenScopeActive(screenType)) { // in case it's coming back from background
            logger.log(loggerText+" (reinitialized scope)")
            initScreenScope(screenType)
            callOnInit()
        } else {
            logger.log(loggerText)
        }
        return currentState
    }

    // only called by the State Reducers
    inline fun <reified T:ScreenState> updateScreen(
        stateClass: KClass<T>,
        update: (T) -> T,
    ) {
        val logger = Logger("StateManager.updateScreen")
        logger.log("updateScreen: "+T::class.simpleName)
        val screenType = getScreenType(stateClass)
        val currentState = screenStatesMap[screenType] as? T
        if (currentState != null) { // only perform update if the state class object is currently inside the screenStatesMap
            screenStatesMap[screenType] = update(currentState)
            // only trigger recomposition if screen state has changed
            if (!currentState.equals(screenStatesMap[screenType])) {
                triggerRecomposition()
                logger.log(T::class.simpleName+" changed: recomposition is triggered")
            }
        }
    }

    fun triggerRecomposition() {
        mutableStateFlow.value = AppState(mutableStateFlow.value.recompositionIndex+1)
    }

    fun initScreenScope(screenType : ScreenType) {
        logger.log("initScreenScope($screenType)")
        screenScopesMap[screenType]?.cancel() // cancel old scope if that exists
        screenScopesMap[screenType] = CoroutineScope(Job() + Dispatchers.Main) // create new scope
    }

    fun getScreenScope(stateClass : KClass<out ScreenState>) : CoroutineScope? {
        val screenType = getScreenType(stateClass)
        return screenScopesMap[screenType]
    }

    fun getScreenState(stateClass : KClass<out ScreenState>) : ScreenState? {
        val screenType = getScreenType(stateClass)
        return screenStatesMap[screenType]
    }

    fun isScreenScopeActive(screenType : ScreenType) : Boolean {
        return screenScopesMap[screenType]?.isActive == true
    }

    fun cancelScreenScopes() {
        logger.log("cancelScreenScopes()")
        screenScopesMap.forEach {
            it.value.cancel() // cancel screen's coroutine scope
        }
    }

}

data class AppState (
    val recompositionIndex : Int = 0
) {
    fun getStateProviders(model : DKMPViewModel) : StateProviders {
        return model.stateProviders
    }
}











