package com.bapspatil.elon.usecase

import com.bapspatil.elon.model.NasaImage
import io.reactivex.Single

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

interface ImagesUseCase {
    fun getImages(query: String?, mediaType: String?, yearStart: Int?, yearEnd: Int?): Single<List<NasaImage>>
}