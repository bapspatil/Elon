package com.bapspatil.elon.api.model


import com.google.gson.annotations.SerializedName

data class ApiLink(
    @SerializedName("href")
    val href: String?, // https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e002105/GSFC_20171208_Archive_e002105~thumb.jpg
    @SerializedName("rel")
    val rel: String?, // preview
    @SerializedName("render")
    val render: String? // image
)