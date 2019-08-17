package com.bapspatil.elon.usecase

import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.repo.ImagesRepository
import com.bapspatil.elon.util.DateUtils
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
     * Gets images from the API and maps it to a List of NasaImages
     * Can be extended to include more complex business logic in the future
     */
    override fun getImages(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<List<NasaImage>> {
        return imagesRepository.getImagesFromApi(query, mediaType, yearStart, yearEnd)
            .map { data -> data.collection.items }
            .toObservable()
            .flatMapIterable { it }
            .map { nasaItem ->
                NasaImage(
                    image = nasaItem.links[0].href,
                    title = nasaItem.data[0].title,
                    description = nasaItem.data[0].description.replace("  ", "<br><br>"),
                    center = nasaItem.data[0].center,
                    date = DateUtils.getLocalDateFromString(nasaItem.data[0].dateCreated)
                )
            }
            .toList()
    }

}