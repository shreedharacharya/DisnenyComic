package com.example.shreedhar.disneycomic.base

sealed class Result<out T> {
    data class Success<out T>(val result: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$result]"
            is Error -> "Error[exception=$error]"
        }
    }
}
