package com.example.shreedhar.disneycomic.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicDetailsDto(
    val id: Int,
    val title: String,
    val description: String,
    val coverImageUrl: String,
    val images: List<String> = emptyList()
) : Parcelable

fun ComicDetails.toComicDetailsDto(): ComicDetailsDto {
    return if (data.results.isEmpty()) {
        ComicDetailsDto(0, "", "", "")
    } else {
        val result = data.results[0]
        ComicDetailsDto(
            id = result.id,
            title = result.title,
            description = result.description,
            coverImageUrl = result.thumbnail.let {
                "${it.path}.${it.extension}"
            },
            images = result.images.map {
                "${it.path}.${it.extension}"
            }.toMutableList().let {
                // NOTE: this modification is just to show multiple images in the View.
                if (it.size < 5) {
                    while (it.size < 5) {
                        it.addAll(it)
                    }
                    return@let it
                }
                it
            }
        )
    }
}