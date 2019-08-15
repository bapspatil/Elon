package com.bapspatil.elon.api.model


import com.google.gson.annotations.SerializedName

data class ApiMetadata(
    @SerializedName("total_hits")
    val totalHits: Int? // 84
)