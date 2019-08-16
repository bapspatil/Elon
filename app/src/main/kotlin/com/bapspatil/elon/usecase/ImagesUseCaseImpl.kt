package com.bapspatil.elon.usecase

import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.repo.ImagesRepository
import io.reactivex.Single
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Use case implementation for getting the images
 * This is used for all business logic of the images
 */
class ImagesUseCaseImpl
@Inject constructor(private val imagesRepository: ImagesRepository) : ImagesUseCase {

    /**
     * Gets images from the API for now
     * Can be extended to include business logic
     */
    override fun getImages(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<List<NasaImage>> {
        return imagesRepository.getImagesFromApi(query, mediaType, yearStart, yearEnd)
    }

}