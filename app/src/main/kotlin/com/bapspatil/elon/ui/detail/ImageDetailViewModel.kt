package com.bapspatil.elon.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.ui.base.BaseViewModel
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * ViewModel responsible for the business logic associated to image detail
 */
class ImageDetailViewModel
@Inject constructor() : BaseViewModel<ImageDetailViewState>() {

    private lateinit var image: NasaImage

    /**
     * Set the image
     *  @param image `NasaImage` associated
     */
    fun setImage(image: NasaImage?) = if (image != null) {
        this.image = image
        emitViewState(ImageDetailViewState.RenderImageDetail(image))
    } else {
        emitViewState(ImageDetailViewState.Error)
    }

    class Factory
    @Inject constructor() : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ImageDetailViewModel() as T
        }
    }

}