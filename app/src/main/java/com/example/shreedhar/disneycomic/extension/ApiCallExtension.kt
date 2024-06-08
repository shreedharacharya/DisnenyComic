package com.example.shreedhar.disneycomic.extension

import com.example.shreedhar.disneycomic.base.Result

suspend fun <T : Any> apiCall(call: suspend () -> T): Result<T> {
    return try {
        val response = call()
        Result.Success(response)
    } catch (ex: Throwable) {
        // Check exceptions here
       // if (ex is HttpException) { }
        Result.Error(ex)
    }
}