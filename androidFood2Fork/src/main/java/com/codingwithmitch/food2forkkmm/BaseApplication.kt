package com.codingwithmitch.food2forkkmm

import android.app.Application
import com.codingwithmitch.food2forkkmm.di.recipeServiceModule
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(recipeServiceModule)
        }
    }
}