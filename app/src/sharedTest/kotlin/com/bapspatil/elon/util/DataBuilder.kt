package com.bapspatil.elon.util

import com.bapspatil.elon.api.model.*
import com.bapspatil.elon.model.NasaImage
import org.threeten.bp.LocalDate

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

object DataBuilder {

    fun getImages(): List<NasaImage> = listOf(
        NasaImage(
            "https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e001362/GSFC_20171208_Archive_e001362~thumb.jpg",
            "Title 1",
            "Description 1",
            "Center 1",
            LocalDate.of(2017, 1, 1)
        ),
        NasaImage(
            "https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e000261/GSFC_20171208_Archive_e000261~thumb.jpg",
            "Title 2",
            "Description 2",
            "Center 2",
            LocalDate.of(2017, 2, 2)
        ),
        NasaImage(
            "https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e001738/GSFC_20171208_Archive_e001738~thumb.jpg",
            "Title 3",
            "Description 3",
            "Center 3",
            LocalDate.of(2017, 3, 3)
        ),
        NasaImage(
            "https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e000148/GSFC_20171208_Archive_e000148~thumb.jpg",
            "Title 4",
            "Description 4",
            "Center 4",
            LocalDate.of(2017, 4, 4)
        )
    )

    fun getImageDetail(): NasaImage = NasaImage(
        "https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e001362/GSFC_20171208_Archive_e001362~thumb.jpg",
        "Title 1",
        "Description 1",
        "Center 1",
        LocalDate.of(2017, 1, 1)
    )

    const val DEFAULT_QUERY = "milky way"
    const val DEFAULT_MEDIA_TYPE = "image"
    const val DEFAULT_YEAR_START = 2017
    const val DEFAULT_YEAR_END = 2017

    fun getNasaResponse(): NasaResponse = NasaResponse(
        NasaCollection(
            version = "1.0",
            href = "href",
            items = listOf(
                NasaItem(
                    links = listOf(
                        NasaLink(
                            href = getImages()[0].image,
                            rel = "rel",
                            render = "render"
                        )
                    ),
                    href = "href",
                    data = listOf(
                        NasaData(
                            description = getImages()[0].description,
                            center = getImages()[0].center,
                            title = getImages()[0].title,
                            album = listOf(),
                            dateCreated = "2017-01-01T00:00:00Z",
                            keywords = listOf(),
                            location = "location",
                            mediaType = "mediaType",
                            nasaId = "nasaId"
                        )
                    )
                ),
                NasaItem(
                    links = listOf(
                        NasaLink(
                            href = getImages()[1].image,
                            rel = "rel",
                            render = "render"
                        )
                    ),
                    href = "href",
                    data = listOf(
                        NasaData(
                            description = getImages()[1].description,
                            center = getImages()[1].center,
                            title = getImages()[1].title,
                            album = listOf(),
                            dateCreated = "2017-02-02T00:00:00Z",
                            keywords = listOf(),
                            location = "location",
                            mediaType = "mediaType",
                            nasaId = "nasaId"
                        )
                    )
                ),
                NasaItem(
                    links = listOf(
                        NasaLink(
                            href = getImages()[2].image,
                            rel = "rel",
                            render = "render"
                        )
                    ),
                    href = "href",
                    data = listOf(
                        NasaData(
                            description = getImages()[2].description,
                            center = getImages()[2].center,
                            title = getImages()[2].title,
                            album = listOf(),
                            dateCreated = "2017-03-03T00:00:00Z",
                            keywords = listOf(),
                            location = "location",
                            mediaType = "mediaType",
                            nasaId = "nasaId"
                        )
                    )
                ),
                NasaItem(
                    links = listOf(
                        NasaLink(
                            href = getImages()[3].image,
                            rel = "rel",
                            render = "render"
                        )
                    ),
                    href = "href",
                    data = listOf(
                        NasaData(
                            description = getImages()[3].description,
                            center = getImages()[3].center,
                            title = getImages()[3].title,
                            album = listOf(),
                            dateCreated = "2017-04-04T00:00:00Z",
                            keywords = listOf(),
                            location = "location",
                            mediaType = "mediaType",
                            nasaId = "nasaId"
                        )
                    )
                )
            ),
            links = emptyList(),
            metadata = NasaMetadata(totalHits = 0)
        )
    )
}