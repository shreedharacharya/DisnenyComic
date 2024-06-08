package com.example.shreedhar.disneycomic.data.repository

import androidx.annotation.VisibleForTesting
import com.example.shreedhar.disneycomic.data.remote.service.ComicService
import javax.inject.Inject

class ComicRepository @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val service: ComicService
) {

    suspend fun getComicDetails(
        comicId: Int,
    ) = service.getComicDetails(comicId)

}