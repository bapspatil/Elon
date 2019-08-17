package com.bapspatil.elon.util

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

    fun getImageDetail() = NasaImage(
        "https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e001362/GSFC_20171208_Archive_e001362~thumb.jpg",
        "Title 1",
        "Description 1",
        "Center 1",
        LocalDate.of(2017, 1, 1)
    )

}