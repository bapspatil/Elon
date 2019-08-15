package com.bapspatil.elon.usecase

import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.repo.ImagesRepository
import io.reactivex.Single
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

class ImagesUseCaseImpl
@Inject constructor(private val imagesRepository: ImagesRepository) : ImagesUseCase {

    override fun getImages(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<List<NasaImage>> {
        return imagesRepository.getImagesFromApi(query, mediaType, yearStart, yearEnd)
    }

}