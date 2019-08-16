package com.bapspatil.elon.repo

import com.bapspatil.elon.api.NasaService
import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.util.DateUtils
import com.bapspatil.elon.util.io
import io.reactivex.Single
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Repository implementation for getting the images from API
 * This can later be extended to Database too
 */
class ImagesRepositoryImpl
@Inject constructor(private val nasaService: NasaService) : ImagesRepository {

    /**
     * Gets images from API and maps it to `NasaImage`
     */
    override fun getImagesFromApi(
        query: String?,
        mediaType: String?,
        yearStart: Int?,
        yearEnd: Int?
    ): Single<List<NasaImage>> {
        return nasaService.getNasaData(query, mediaType, yearStart, yearEnd)
            .io()
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