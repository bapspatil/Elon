package com.bapspatil.elon.api.model


import com.google.gson.annotations.SerializedName

data class ApiCollection(
    @SerializedName("href")
    val href: String?, // https://images-api.nasa.gov/search?q=milky%20way&media_type=image&year_start=2017&
    @SerializedName("items")
    val items: ArrayList<ApiItem?>?,
    @SerializedName("metadata")
    val metadata: ApiMetadata?,
    @SerializedName("version")
    val version: String? // 1.0
)