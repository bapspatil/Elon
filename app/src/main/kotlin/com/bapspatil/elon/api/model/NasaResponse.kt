package com.bapspatil.elon.api.model


import com.google.gson.annotations.SerializedName

data class NasaResponse(
    @SerializedName("collection")
    val collection: NasaCollection
)