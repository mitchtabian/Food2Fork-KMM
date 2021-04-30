package com.codingwithmitch.food2forkkmm.viewmodel

import com.codingwithmitch.food2forkkmm.datasource.Repository

class StateReducers(stateManager: StateManager, repository: Repository) {

    internal val stateManager by lazy { stateManager }

    internal val dataRepository by lazy { repository }

}
