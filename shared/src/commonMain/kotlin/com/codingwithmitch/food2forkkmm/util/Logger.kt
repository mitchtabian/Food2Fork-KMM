package com.codingwithmitch.food2forkkmm.util

expect class Logger(
    className: String,
) {
    fun log(msg: String)
}

fun printLogD(className: String?, message: String ) {
    println("$className: $message")
}
