package com.bapspatil.elon.api.model


import com.google.gson.annotations.SerializedName

data class NasaData(
    @SerializedName("album")
    val album: ArrayList<String>,
    @SerializedName("center")
    val center: String, // GSFC
    @SerializedName("date_created")
    val dateCreated: String, // 2017-12-08T00:00:00Z
    @SerializedName("description")
    val description: String, // Description of the image goes here.
    @SerializedName("keywords")
    val keywords: ArrayList<String>,
    @SerializedName("location")
    val location: String, // Greenbelt, MD
    @SerializedName("media_type")
    val mediaType: String, // image
    @SerializedName("nasa_id")
    val nasaId: String, // GSFC_20171208_Archive_e002105
    @SerializedName("title")
    val title: String // NASA's Hubble Universe in 3-D
)