package com.codingwithmitch.food2forkkmm.util

expect class BuildConfig() {
    fun isDebug(): Boolean

    fun isAndroid(): Boolean // true is android client, false if iOS
}