package com.example.shreedhar.disneycomic.presentation


import com.example.shreedhar.disneycomic.base.BaseViewState
import com.example.shreedhar.disneycomic.base.Result
import com.example.shreedhar.disneycomic.data.model.ComicDetailsDto
import com.example.shreedhar.disneycomic.domain.usecase.GetComicDetails
import com.example.shreedhar.disneycomic.testutils.MockkUnitTest
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class ComicDetailsViewModelTest : MockkUnitTest() {

    @RelaxedMockK
    lateinit var getComicDetails: GetComicDetails

    @SpyK
    @InjectMockKs
    lateinit var viewModel: ComicDetailsViewModel

    @Test
    fun verifyOnTriggerEventLoadDetailWithComicId() = runTest {
        // Arrange (Given)
        val comicId = 1

        // Act (When)
        viewModel.onTriggerEvent(ComicDetailEvent.LoadDetail(comicId))

        // Assert (Then)
        coVerify { getComicDetails.invoke(comicId) }
    }

    @Test
    fun verifyOnTriggerEventLoadDetailCheckState() = runTest {
        // Arrange (Given)
        val comicId = 1
        val dto = mockk<ComicDetailsDto>()
        coEvery { getComicDetails.invoke(comicId) } returns flow {
            emit(Result.Success(dto))
        }

        // Act (When)
        viewModel.onTriggerEvent(ComicDetailEvent.LoadDetail(comicId))

        // Assert (Then)
        viewModel.uiState.value.apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
        }
    }

    @Test
    fun verifyOnTriggerEventLoadDetail_CheckError() = runTest {
        // given
        val comicId = 1
        coEvery { getComicDetails(any()) } returns flow {
            emit(Result.Error(IOException("this is a test exception.")))
        }

        // when
        viewModel.onTriggerEvent(ComicDetailEvent.LoadDetail(comicId))

        // then
        coVerify(exactly = 1) { getComicDetails(any()) }
        confirmVerified(getComicDetails)
    }
}