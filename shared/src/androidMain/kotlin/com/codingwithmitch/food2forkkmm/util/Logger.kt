package com.codingwithmitch.food2forkkmm.util

import com.codingwithmitch.food2forkkmm.BuildConfig

actual class Logger actual constructor(
    private val className: String
) {

    actual fun log(msg: String) {
        if (!BuildConfig.DEBUG) {
            // production logging - Crashlytics or something else
        }
        else{
            printLogD(className, msg)
        }
    }
}






