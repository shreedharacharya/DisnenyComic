package com.example.shreedhar.disneycomic.extension

inline fun <reified T : Any> Any.cast(): T {
    return this as T
}