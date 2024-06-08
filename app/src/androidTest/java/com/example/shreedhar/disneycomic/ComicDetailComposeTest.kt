package com.example.shreedhar.disneycomic

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shreedhar.disneycomic.data.model.ComicDetailsDto
import com.example.shreedhar.disneycomic.presentation.view.ComicDetailsContent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComicDetailsComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkHasTitleAndDescription() {
        startComicDetails()
        composeTestRule.onNodeWithText("Title").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description").assertIsDisplayed()
    }

    @Test
    fun checkNoDescription() {
        startComicDetails(comicDtoForTesting(description = ""))
        composeTestRule.onNodeWithText("").assertDoesNotExist()
    }

    @Test
    fun checkClickOnDescription() {
        val description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever " +
                "since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only"
        startComicDetails(comicDtoForTesting(description = description))
        val updatedText = "${description.substring(0, 200)}..."
        composeTestRule.onNodeWithText(updatedText).assertIsDisplayed()
        composeTestRule.onNodeWithText(updatedText).performClick()
        composeTestRule.onNodeWithText(description).assertIsDisplayed()
    }

    @Test
    fun checkCoverImage() {
        startComicDetails(comicDtoForTesting(description = ""))
        composeTestRule.onNodeWithContentDescription("cover image").assertIsDisplayed()
    }


    private fun startComicDetails(dto: ComicDetailsDto = comicDtoForTesting()) {
        composeTestRule.setContent {
            ComicDetailsContent(
                dto = dto,
            )
        }
    }

    private fun comicDtoForTesting(id: Int = 1, title: String = "Title", description: String = "Description", coverImageUrl: String = "image.jpg") = ComicDetailsDto(
        id = id,
        title = title,
        description = description,
        coverImageUrl = coverImageUrl
    )
}