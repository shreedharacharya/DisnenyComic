package com.example.shreedhar.disneycomic.presentation

import com.example.shreedhar.disneycomic.data.model.ComicDetailsDto
import org.junit.Assert
import org.junit.Test

class ComicDetailsContractTest {

    private lateinit var event: ComicDetailEvent

    private lateinit var state: ComicDetailViewState

    @Test
    fun verifyEventLoadDetail() {
        val comicId = 1
        event = ComicDetailEvent.LoadDetail(comicId)

        val eventLoadDetail = event as ComicDetailEvent.LoadDetail
        Assert.assertEquals(comicId, eventLoadDetail.comicId)
    }

    @Test
    fun verifyStateComicDetail() {
        val dto = getComicDetailsDto()
        state = ComicDetailViewState(dto)

        Assert.assertEquals(dto, state.comicDto)
    }
}

fun getComicDetailsDto(): ComicDetailsDto {
    return ComicDetailsDto(
        id = 1,
        title = "Title",
        description = "Description",
        coverImageUrl = "image.jpg"
    )
}