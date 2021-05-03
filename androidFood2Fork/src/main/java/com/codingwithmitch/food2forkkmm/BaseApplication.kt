package com.codingwithmitch.food2forkkmm

import android.app.Application
import com.codingwithmitch.food2forkkmm.util.Logger
import dagger.hilt.android.HiltAndroidApp

val logger = Logger("AppDebug")

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}