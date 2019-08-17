package com.bapspatil.elon.usecase

import com.bapspatil.elon.repo.ImagesRepository
import com.bapspatil.elon.rule.BaseRule
import com.bapspatil.elon.util.DataBuilder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@RunWith(MockitoJUnitRunner::class)
class ImagesUseCaseImplTest : BaseRule() {

    private val imagesRepository: ImagesRepository = mock()
    private lateinit var imagesUseCase: ImagesUseCase

    private val images = DataBuilder.getImages()

    @Before
    fun setUp() {
        imagesUseCase = ImagesUseCaseImpl(imagesRepository)

        whenever(imagesRepository.getImagesFromApi(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END))
            .thenReturn(Single.just(DataBuilder.getNasaResponse()))
    }

    @Test
    fun `Verify getting a list of NasaImages from repository via usecase`() {
        imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(images)
    }

}