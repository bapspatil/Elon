package com.bapspatil.elon.ui.list

import com.bapspatil.elon.model.NasaImage

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * States for images list screen
 */
sealed class ImagesListViewState {
    class DisplayImagesList(val images: List<NasaImage>) : ImagesListViewState()
    class OpenImageDetail(val image: NasaImage) : ImagesListViewState()
    class Error(val error: String? = "") : ImagesListViewState()
    object NoInternet : ImagesListViewState()
    object ShowLoading : ImagesListViewState()
    object DisplayEmptyListMessage : ImagesListViewState()
}