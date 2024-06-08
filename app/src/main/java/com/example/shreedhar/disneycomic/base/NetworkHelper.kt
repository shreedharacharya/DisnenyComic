package com.example.shreedhar.disneycomic.base

import com.example.shreedhar.disneycomic.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val CLIENT_TIME_OUT = 30L

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}

fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }
}

fun createOkHttpClient(httpLoggingInterceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
}

inline fun <reified T> createRetrofitWithMoshi(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    baseUrl: String = BuildConfig.BASE_URL
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    return retrofit.create(T::class.java)
}