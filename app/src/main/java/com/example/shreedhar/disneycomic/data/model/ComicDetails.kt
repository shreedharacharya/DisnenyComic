package com.example.shreedhar.disneycomic.data.model

import com.squareup.moshi.Json


data class ComicDetails(
    @Json(name = "attributionHTML")
    val attributionHTML: String,
    @Json(name = "attributionText")
    val attributionText: String,
    @Json(name = "code")
    val code: Int,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "etag")
    val etag: String,
    @Json(name = "status")
    val status: String
)
data class Data(
    @Json(name = "count")
    val count: Int,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "total")
    val total: Int
)


data class Result(
    @Json(name = "characters")
    val characters: Characters,
    @Json(name = "collectedIssues")
    val collectedIssues: List<Any>,
    @Json(name = "collections")
    val collections: List<Any>,
    @Json(name = "creators")
    val creators: Creators,
    @Json(name = "dates")
    val dates: List<Date>,
    @Json(name = "description")
    val description: String,
    @Json(name = "diamondCode")
    val diamondCode: String,
    @Json(name = "digitalId")
    val digitalId: Int,
    @Json(name = "ean")
    val ean: String,
    @Json(name = "events")
    val events: Events,
    @Json(name = "format")
    val format: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "images")
    val images: List<Thumbnail>,
    @Json(name = "isbn")
    val isbn: String,
    @Json(name = "issn")
    val issn: String,
    @Json(name = "issueNumber")
    val issueNumber: Int,
    @Json(name = "modified")
    val modified: String,
    @Json(name = "pageCount")
    val pageCount: Int,
    @Json(name = "prices")
    val prices: List<Price>,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "series")
    val series: Series,
    @Json(name = "stories")
    val stories: Stories,
    @Json(name = "textObjects")
    val textObjects: List<Any>,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail,
    @Json(name = "title")
    val title: String,
    @Json(name = "upc")
    val upc: String,
    @Json(name = "urls")
    val urls: List<Url>,
    @Json(name = "variantDescription")
    val variantDescription: String,
    @Json(name = "variants")
    val variants: List<Variant>
)


data class Characters(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Any>,
    @Json(name = "returned")
    val returned: Int
)


data class Creators(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "returned")
    val returned: Int
)


data class Date(
    @Json(name = "date")
    val date: String,
    @Json(name = "type")
    val type: String
)


data class Events(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<Any>,
    @Json(name = "returned")
    val returned: Int
)


data class Item(
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "role")
    val role: String
)


data class ItemX(
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String,
    @Json(name = "type")
    val type: String
)


data class Price(
    @Json(name = "price")
    val price: Double,
    @Json(name = "type")
    val type: String
)


data class Series(
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String
)


data class Stories(
    @Json(name = "available")
    val available: Int,
    @Json(name = "collectionURI")
    val collectionURI: String,
    @Json(name = "items")
    val items: List<ItemX>,
    @Json(name = "returned")
    val returned: Int
)


data class Thumbnail(
    @Json(name = "extension")
    val extension: String,
    @Json(name = "path")
    val path: String
)


data class Url(
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)


data class Variant(
    @Json(name = "name")
    val name: String,
    @Json(name = "resourceURI")
    val resourceURI: String
)