package com.bapspatil.elon.repo

import com.bapspatil.elon.model.NasaImage
import io.reactivex.Single

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Repository for getting the images from API
 * This can later be extended to Database too
 */
interface ImagesRepository {

    /**
     * Gets images from API
     */
    fun getImagesFromApi(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<List<NasaImage>>

}