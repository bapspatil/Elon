package com.bapspatil.elon.api.model


import com.google.gson.annotations.SerializedName

data class ApiNasaResponse(
    @SerializedName("collection")
    val collection: ApiCollection?
)