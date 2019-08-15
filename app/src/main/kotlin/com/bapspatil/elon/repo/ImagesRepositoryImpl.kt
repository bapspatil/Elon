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

class ImagesRepositoryImpl
@Inject constructor(private val nasaService: NasaService) : ImagesRepository {

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
                    description = nasaItem.data[0].description,
                    center = nasaItem.data[0].center,
                    date = DateUtils.getLocalDateFromString(nasaItem.data[0].dateCreated)
                )
            }
            .toList()
    }

    // TODO: Get images with ResponseBody after parsing

}