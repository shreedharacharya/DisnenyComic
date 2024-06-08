package com.example.shreedhar.disneycomic.presentation

import com.example.shreedhar.disneycomic.data.model.ComicDetailsDto

data class ComicDetailViewState(
    val comicDto: ComicDetailsDto
)

sealed class ComicDetailEvent {
    data class LoadDetail(val comicId: Int) : ComicDetailEvent()
}