package com.bapspatil.elon.api.model


import com.google.gson.annotations.SerializedName

data class NasaCollection(
        @SerializedName("href")
        val href: String, // https://images-api.nasa.gov/search?q=milky%20way&media_type=image&year_start=2017&
        @SerializedName("items")
        val items: List<NasaItem>,
        @SerializedName("metadata")
        val metadata: NasaMetadata,
        @SerializedName("links")
        val links: List<NasaCollectionLink>,
        @SerializedName("version")
        val version: String // 1.0
)