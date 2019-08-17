package com.bapspatil.elon.usecase

import com.bapspatil.elon.model.NasaImage
import io.reactivex.Single

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Use case for getting the images
 * This is used for all business logic of the images
 */
interface ImagesUseCase {

    /**
     * Gets images from the API for now
     * Can be extended to include more complex business logic in the future
     */
    fun getImages(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<List<NasaImage>>

}