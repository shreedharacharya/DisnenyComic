package com.example.shreedhar.disneycomic.di

import com.example.shreedhar.disneycomic.base.createHttpLoggingInterceptor
import com.example.shreedhar.disneycomic.base.createMoshi
import com.example.shreedhar.disneycomic.base.createOkHttpClient
import com.example.shreedhar.disneycomic.base.createRetrofitWithMoshi
import com.example.shreedhar.disneycomic.data.remote.service.ComicService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return createMoshi()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return createHttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return createOkHttpClient(httpLoggingInterceptor)
    }

    @Provides
    @Singleton
    fun provideComicService(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): ComicService {
        return createRetrofitWithMoshi(okHttpClient, moshi)
    }
}