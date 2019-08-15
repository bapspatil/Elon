package com.bapspatil.elon.repo

import com.bapspatil.elon.model.NasaImage
import io.reactivex.Single

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

interface ImagesRepository {
    fun getImagesFromApi(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<List<NasaImage>>
}