package com.bapspatil.elon.api.model

import com.google.gson.annotations.SerializedName

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

data class NasaCollectionLink(
        @SerializedName("href")
        val href: String, // https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e002105/GSFC_20171208_Archive_e002105~thumb.jpg
        @SerializedName("prompt")
        val prompt: String, // Next
        @SerializedName("rel")
        val rel: String // next
)