package com.bapspatil.elon.ui.list

import com.bapspatil.elon.api.HttpErrorUtils
import com.bapspatil.elon.rule.BaseRule
import com.bapspatil.elon.usecase.ImagesUseCase
import com.bapspatil.elon.util.DataBuilder
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@RunWith(MockitoJUnitRunner::class)
class ImagesListViewModelTest : BaseRule() {

    private lateinit var viewModel: ImagesListViewModel

    private val imagesUseCase: ImagesUseCase = mock()
    private val images = DataBuilder.getImages()

    @Before
    fun setUp() {
        viewModel = ImagesListViewModel(imagesUseCase, HttpErrorUtils())
    }

    @Test
    fun `Given a list of images when ViewModel is created then display this list`() {
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.just(images))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())
        val state = testObserver
            .assertNoErrors()
            .values()[1]

        assertThat(state.cast<ImagesListViewState.DisplayImagesList>().images).isEqualTo(images)
    }

    @Test
    fun `Given the ViewModel created when the images list is loading then display the loader`() {
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.just(images))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())
        val state = testObserver
            .assertNoErrors()
            .values()[0]

        assertThat(state is ImagesListViewState.ShowLoading).isTrue()
    }

    @Test
    fun `Given the ViewModel created when the refresh button is clicked then load the images list`() {
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.just(images))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onReload()
        val state = testObserver
            .assertNoErrors()
            .values()[0]

        assertThat(state is ImagesListViewState.ShowLoading).isTrue()
    }

    @Test
    fun `Given an empty list of images when viewModel is created then send DisplayEmptyListMessage`() {
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.just(emptyList()))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())
        val state = testObserver
            .assertNoErrors()
            .values()[1]

        assertThat(state is ImagesListViewState.DisplayEmptyListMessage).isTrue()
    }

    @Test
    fun `Given an error when loading the images then send an error`() {
        val errorMsg = "errorMsg"
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.error(Throwable(errorMsg)))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())
        val state = testObserver
            .assertNoErrors()
            .values()[1]

        assertThat(state.cast<ImagesListViewState.Error>().error).isEqualTo(errorMsg)
    }

    @Test
    fun `Given no internet connection when loading the comments then emit no internet action`() {
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.error(UnknownHostException()))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())
        val state = testObserver
            .assertNoErrors()
            .values()[1]

        assertThat(state is ImagesListViewState.NoInternet).isTrue()
    }


    @Test
    fun `Given an actual list of images when a row is clicked then emit a OpenImageDetail with the image`() {
        val testObserver = viewModel.viewStateObservable.test()
        val image = DataBuilder.getImageDetail()

        viewModel.onRowClicked(image)
        val state = testObserver
            .assertNoErrors()
            .values()[0]

        assertThat(state.cast<ImagesListViewState.OpenImageDetail>().image).isEqualTo(image)
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        internal fun <T> ImagesListViewState.cast(): T = this as T
    }
}
