package com.bapspatil.elon.ui.list

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bapspatil.elon.api.HttpErrorUtils
import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.ui.base.BaseViewModel
import com.bapspatil.elon.usecase.ImagesUseCase
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * ViewModel responsible for the business logic associated to the list of images
 */
class ImagesListViewModel
@Inject constructor(
        private val imagesUseCase: ImagesUseCase,
        private val httpErrorUtils: HttpErrorUtils
) : BaseViewModel<ImagesListViewState>(), DefaultLifecycleObserver {

    /**
     * Associated to the lifecycle of the activity; load the list of images when the activity
     * is created
     *  @param owner LifecycleOwner
     */
    override fun onCreate(owner: LifecycleOwner) {
        // Load images when Activity is created
        loadImages()
    }

    /**
     * Reload images list
     */
    fun onReload() {
        loadImages()
    }

    /**
     * Callback when a row of the list of images is clicked; emit a OpenImageDetail
     * action with the image associated to the row clicked
     *  @param image `NasaImage` associated to the row clicked
     */
    fun onRowClicked(image: NasaImage) {
        emitViewState(ImagesListViewState.OpenImageDetail(image))
    }

    /**
     * Load the list of images to display
     */
    private fun loadImages() {
        disposables.add(
                imagesUseCase
                        .getImages(DEFAULT_QUERY, DEFAULT_MEDIA_TYPE, DEFAULT_YEAR_START, DEFAULT_YEAR_END)
                        .doOnSubscribe { emitViewState(ImagesListViewState.ShowLoading) }
                        .subscribe(this::success, this::error)
        )
    }

    /**
     * Emit the list of images if not empty, otherwise emit a message to the user to inform that the list is empty
     *  @param images `NasaImage`s loaded
     */
    private fun success(images: List<NasaImage>) {
        if (images.isEmpty())
            emitViewState(ImagesListViewState.DisplayEmptyListMessage)
        else
            emitViewState(ImagesListViewState.DisplayImagesList(images))
    }

    /**
     * Error thrown from the request to lost the image. Emit the error with the message to the view
     */
    private fun error(throwable: Throwable) {
        when (httpErrorUtils.hasLostInternet(throwable)) {
            true -> emitViewState(ImagesListViewState.NoInternet)
            false -> emitViewState(ImagesListViewState.Error(throwable.message))
        }
    }

    class Factory
    @Inject constructor(
            private val imagesUseCase: ImagesUseCase,
            private val httpErrorUtils: HttpErrorUtils
    ) : ViewModelProvider.Factory {
        @SuppressWarnings("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ImagesListViewModel(imagesUseCase, httpErrorUtils) as T
        }
    }

    companion object {
        const val DEFAULT_QUERY = "milky way"
        const val DEFAULT_MEDIA_TYPE = "image"
        const val DEFAULT_YEAR_START = 2017
        const val DEFAULT_YEAR_END = 2017
    }
}