package com.codingwithmitch.food2forkkmm.viewmodel

import com.codingwithmitch.food2forkkmm.viewmodel.screens.recipe_list.RecipeListState
import kotlin.reflect.KClass

class StateProviders (stateManager : StateManager, events : Events) {

    internal val stateManager by lazy { stateManager }

    internal val events by lazy { events }

}

// here we define all the screenTypes
// the AppState keeps in memory just one screenState per screenType
// in order to support dual-pane, it makes sense to have at least a MASTER and a DETAIL
enum class ScreenType{ MASTER, DETAIL, DIALOG }

interface ScreenState // we apply this empty interface to all screen state data classes

// here we list all screenState classes, defining their screenType
fun getScreenType(stateClass : KClass<out ScreenState>) : ScreenType {
    return when (stateClass) {
        RecipeListState::class -> ScreenType.MASTER
//        CountryDetailState::class -> ScreenType.DETAIL
        // ProfileState::class -> ScreenType.MASTER
        // SettingsState::class -> ScreenType.DETAIL
        // DialogState::class -> ScreenType.DIALOG
        else -> ScreenType.MASTER // we default to MASTER
    }
}









