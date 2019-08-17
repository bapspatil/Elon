package com.bapspatil.elon.ui.detail

import com.bapspatil.elon.rule.BaseRule
import com.bapspatil.elon.util.DataBuilder
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@RunWith(MockitoJUnitRunner::class)
class ImageDetailViewModelTest : BaseRule() {

    private lateinit var viewModel: ImageDetailViewModel

    private val image = DataBuilder.getImageDetail()

    @Before
    fun setUp() {
        viewModel = ImageDetailViewModel()
    }

    @Test
    fun `Given the ViewModel sets an image RenderImageDetail with image is emitted`() {
        val testObserver = viewModel.viewStateObservable.test()

        viewModel.setImage(image)
        val state = testObserver
            .assertNoErrors()
            .values()[0]

        assertThat(state.cast<ImageDetailViewState.RenderImageDetail>().image).isEqualTo(image)
    }

    @Test
    fun `Given the ViewModel fails to set an image an Error is emitted`() {
        val testObserver = viewModel.viewStateObservable.test()

        viewModel.setImage(null)
        val state = testObserver
            .assertNoErrors()
            .values()[0]

        assertThat(state is ImageDetailViewState.Error).isTrue()
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        internal fun <T> ImageDetailViewState.cast(): T = this as T
    }
}