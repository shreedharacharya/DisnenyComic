package com.example.shreedhar.disneycomic.domain.usecase

import androidx.annotation.VisibleForTesting
import com.example.shreedhar.disneycomic.base.Result
import com.example.shreedhar.disneycomic.base.usecase.DataStateUseCase
import com.example.shreedhar.disneycomic.data.model.ComicDetailsDto
import com.example.shreedhar.disneycomic.data.model.toComicDetailsDto
import com.example.shreedhar.disneycomic.data.repository.ComicRepository
import com.example.shreedhar.disneycomic.extension.apiCall
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetComicDetails @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val comicRepo: ComicRepository
) : DataStateUseCase<Int, ComicDetailsDto>() {
    override suspend fun FlowCollector<Result<ComicDetailsDto>>.execute(comicsId: Int) {
        val result = apiCall { comicRepo.getComicDetails(comicsId).toComicDetailsDto() }
        emit(result)
    }
}