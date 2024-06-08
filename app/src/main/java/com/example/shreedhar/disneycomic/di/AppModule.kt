package com.example.shreedhar.disneycomic.di

import com.example.shreedhar.disneycomic.app.ComicApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

  @Provides
  @Singleton
  fun provideApplication(): ComicApp {
    return ComicApp()
  }
}
