package com.bapspatil.elon.usecase

import com.bapspatil.elon.api.model.NasaItem
import com.bapspatil.elon.repo.ImagesRepository
import io.reactivex.Single
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

class ImagesUseCaseImpl
@Inject constructor(private val imagesRepository: ImagesRepository) : ImagesUseCase {

    override fun getImages(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<ArrayList<NasaItem>> {
        return imagesRepository.getImagesFromApi(query, mediaType, yearStart, yearEnd)
    }

}