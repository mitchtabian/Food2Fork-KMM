package com.codingwithmitch.food2forkkmm.domain.util

data class DataState<out T>(
    val data: T? = null,
    val message: String? = null,
    val loading: Boolean = false,
){
    companion object{

        fun <T> success(
            data: T
        ): DataState<T>{
            return DataState(
                data = data,
            )
        }

        fun <T> error(
            message: String,
        ): DataState<T>{
            return DataState(
                message = message
            )
        }

        fun <T> loading(): DataState<T> = DataState(loading = true)
    }
}