package com.example.shreedhar.disneycomic.data.repository

import com.example.shreedhar.disneycomic.data.remote.service.ComicService
import com.example.shreedhar.disneycomic.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ComicRepositoryTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var comicService: ComicService

    private lateinit var repository: ComicRepository

    override fun onCreate() {
        super.onCreate()
        repository = ComicRepository(comicService)
    }

    @Test
    fun getComicDetailsTest() = runTest {
        // Given
        val id = 1
        val characterOptions = hashMapOf<String, String>()

        val idCaptor = slot<Int>()
        val options = slot<Map<String, String>>()
        // When
        repository.getComicDetails(
            comicId = id,
        )

        // Then
        coVerify {
            comicService.getComicDetails(
                comicId = capture(idCaptor),
                options = capture(options)
            )
        }
        assertEquals(id, idCaptor.captured)
        options.captured.also {
            assertEquals(3, it.size)
            assertEquals(it["apikey"], com.example.shreedhar.disneycomic.BuildConfig.PUBLIC_KEY)
        }
    }
}