package com.example.shreedhar.disneycomic.remote.service

import com.example.shreedhar.disneycomic.BuildConfig
import com.example.shreedhar.disneycomic.data.model.toComicDetailsDto
import com.example.shreedhar.disneycomic.data.remote.service.ComicService
import com.example.shreedhar.disneycomic.testutils.BaseServiceTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ComicServiceTest : BaseServiceTest<ComicService>(ComicService::class) {
    companion object {
        const val MOCK_COMIC_JSON_200 = "mock-responses/comic-details.json"
    }

    override val baseUrl: String
        get() = BuildConfig.BASE_URL

    @Test
    fun requestLiveGetComicDetails() = runTest {
        val response = serviceLive.getComicDetails(comicId = 103948)
        response.toComicDetailsDto().apply {
            Assert.assertEquals(103948, id)
            Assert.assertEquals("I AM IRON MAN TPB (Trade Paperback)", title)
            Assert.assertEquals(
                "Collects I Am Iron Man #1-5 And material from Iron Man (2020) #25.  Never-before-seen stories set in iconic eras! Beneath Iron Man's red-and-gold armor is a " +
                        "hopeless romantic, a genius inventor, a war hero, a billionaire, an Avenger, a person: Tony Stark. Murewa Ayodele and Dotun Akande (MOON KNIGHT: BLACK, WHITE & BLOOD, " +
                        "AVENGERS UNLIMITED) journey through ol' Shellhead's rich history in celebration of his 60th anniversary! Kaiju battles under the sea, alien invasions in the desert, a rescue mission in outer space - all this and more await in a collection ideal for both readers new to Iron Man and long-standing fans of the Golden Avenger!",
                description,
            )
            Assert.assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/9/50/664f66ade10d3.jpg", coverImageUrl)
        }
    }

    @Test
    fun requestGetComicDetails() = runTest {
        enqueueResponse(MOCK_COMIC_JSON_200)
        serviceMock.getComicDetails(comicId = 1)
        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertTrue(request.path!!.startsWith("/v1/public/comics/1"))
    }

    @Test
    fun responseGetComicDetails() = runTest {
        enqueueResponse(MOCK_COMIC_JSON_200)
        val response = serviceMock.getComicDetails(comicId = 10)
        response.toComicDetailsDto().apply {
            Assert.assertEquals(10, id)
            Assert.assertEquals("I AM IRON MAN", title)
            Assert.assertEquals("Collects I Am Iron Man", description)
            Assert.assertEquals("http://i.annihil.us/u/prod/marvel/i/mg/9/50/664f66ade10d3.jpg", coverImageUrl)
        }
    }
}