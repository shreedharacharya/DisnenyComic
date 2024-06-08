package com.example.shreedhar.disneycomic.domain.usecase


import com.example.shreedhar.disneycomic.data.repository.ComicRepository
import com.example.shreedhar.disneycomic.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetComicDetailsTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var comicRepo: ComicRepository

    @SpyK
    @InjectMockKs
    private lateinit var comicDetails: GetComicDetails

    @Test
    fun verifyExecute() = runTest {
        val comicId = -1
        comicDetails.invoke(comicId)
        coVerify { comicDetails.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        val comicId = 1

        // Act (When)
        comicDetails.invoke(comicId).single()

        // Assert (Then)
        coVerify { comicRepo.getComicDetails(comicId = comicId) }
    }
}