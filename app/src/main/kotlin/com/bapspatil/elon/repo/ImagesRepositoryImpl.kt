package com.bapspatil.elon.repo

import com.bapspatil.elon.api.NasaService
import com.bapspatil.elon.api.model.NasaResponse
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
     * Gets images from API
     */
    override fun getImagesFromApi(
            query: String?,
            mediaType: String?,
            yearStart: Int?,
            yearEnd: Int?
    ): Single<NasaResponse> =
            nasaService.getNasaData(query, mediaType, yearStart, yearEnd)
                    .io()

}