package com.bapspatil.elon.repo

import com.bapspatil.elon.api.NasaService
import com.bapspatil.elon.rule.BaseRule
import com.bapspatil.elon.util.DataBuilder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

class ImagesRepositoryImplTest : BaseRule() {

    private val nasaService: NasaService = mock()
    private lateinit var imagesRepository: ImagesRepositoryImpl

    private val imagesResponse = DataBuilder.getNasaResponse()

    @Before
    fun setUp() {
        imagesRepository = ImagesRepositoryImpl(nasaService)
        whenever(nasaService.getNasaData(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END))
            .thenReturn(Single.just(DataBuilder.getNasaResponse()))
    }

    @Test
    fun `Verify getting the images response from API via repository`() {
        imagesRepository.getImagesFromApi(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(imagesResponse)
    }

}