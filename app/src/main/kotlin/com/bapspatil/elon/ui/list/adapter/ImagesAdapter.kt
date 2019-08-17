package com.bapspatil.elon.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bapspatil.elon.model.NasaImage
import io.reactivex.Observable

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Adapter for list of images
 */
abstract class ImagesAdapter : RecyclerView.Adapter<ImagesAdapterImpl.ImageViewHolder>() {

    /**
     * Update the list of images in the adapter from the View
     */
    abstract fun update(list: List<NasaImage>)

    /**
     * Get the clicked image from the adapter
     */
    abstract fun getClickedImage(): Observable<NasaImage>

}