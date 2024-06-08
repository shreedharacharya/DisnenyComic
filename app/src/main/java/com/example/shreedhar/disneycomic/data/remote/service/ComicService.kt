package com.example.shreedhar.disneycomic.data.remote.service

import com.example.shreedhar.disneycomic.data.model.ComicDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.math.BigInteger
import java.security.MessageDigest

interface ComicService {
    @GET("$COMIC/{comicId}")
    suspend fun getComicDetails(
        @Path("comicId") comicId: Int,
        @QueryMap options: Map<String, String> = queryMap()
    ): ComicDetails

    companion object {
        const val COMIC = "v1/public/comics"
    }

    private fun queryMap(): Map<String, String> {
        val timeStamp = System.currentTimeMillis()
        val privateKey = com.example.shreedhar.disneycomic.BuildConfig.PRIVATE_KEY
        val publicKey = com.example.shreedhar.disneycomic.BuildConfig.PUBLIC_KEY
        return mapOf(
            "ts" to timeStamp.toString(),
            "apikey" to publicKey,
            "hash" to getMd5("$timeStamp$privateKey$publicKey")
        )
    }

    private fun getMd5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}