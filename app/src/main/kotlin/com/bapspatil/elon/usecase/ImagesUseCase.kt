package com.bapspatil.elon.usecase

import com.bapspatil.elon.api.model.NasaItem
import io.reactivex.Single

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

interface ImagesUseCase {
    fun getImages(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<ArrayList<NasaItem>>
}