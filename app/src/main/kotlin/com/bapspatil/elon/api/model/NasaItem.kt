package com.bapspatil.elon.api.model


import com.google.gson.annotations.SerializedName

data class NasaItem(
        @SerializedName("data")
    val `data`: ArrayList<NasaData>,
        @SerializedName("href")
    val href: String, // https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e002105/collection.json
        @SerializedName("links")
    val links: ArrayList<NasaLink>
)