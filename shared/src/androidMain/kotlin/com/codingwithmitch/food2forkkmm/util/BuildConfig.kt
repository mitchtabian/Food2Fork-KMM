package com.codingwithmitch.food2forkkmm.util

import com.codingwithmitch.food2forkkmm.BuildConfig

actual class BuildConfig {
    actual fun isDebug() = BuildConfig.DEBUG
    actual fun isAndroid() = true
}