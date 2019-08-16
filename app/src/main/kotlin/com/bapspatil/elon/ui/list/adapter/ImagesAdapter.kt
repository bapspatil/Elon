package com.bapspatil.elon.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bapspatil.elon.model.NasaImage
import io.reactivex.Observable

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

abstract class ImagesAdapter : RecyclerView.Adapter<ImagesAdapterImpl.ImageViewHolder>() {
    abstract fun update(list: List<NasaImage>)
    abstract fun getClickedImage(): Observable<NasaImage>
}