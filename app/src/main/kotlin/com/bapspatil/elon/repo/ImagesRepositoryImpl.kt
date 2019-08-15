package com.bapspatil.elon.repo

import com.bapspatil.elon.api.NasaService
import com.bapspatil.elon.api.model.NasaItem
import com.bapspatil.elon.util.io
import io.reactivex.Single
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

class ImagesRepositoryImpl
@Inject constructor(private val nasaService: NasaService) : ImagesRepository {

    override fun getImagesFromApi(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<ArrayList<NasaItem>> {
        return nasaService.getNasaData(query, mediaType, yearStart, yearEnd)
                .io()
                .map { data -> data.collection.items }
    }

    // TODO: Get images with ResponseBody after parsing

}