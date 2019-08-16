package com.bapspatil.elon.ui.detail

import com.bapspatil.elon.model.NasaImage

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

sealed class ImageDetailViewState {
    class RenderImageDetail(val image: NasaImage): ImageDetailViewState()
    object Error: ImageDetailViewState()
}