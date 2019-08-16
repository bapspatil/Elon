package com.bapspatil.elon.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bapspatil.elon.R
import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.util.DiffCallback
import com.bapspatil.elon.util.setCenterAndDate
import com.bapspatil.elon.util.setImageWithGlide
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_nasa_image.view.*
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

class ImagesAdapterImpl
@Inject constructor(private val diffCallback: DiffCallback) : ImagesAdapter() {

    private val images = ArrayList<NasaImage>()
    private val clickSubject = PublishSubject.create<NasaImage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nasa_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position], clickSubject)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(nasaImage: NasaImage, clickSubject: PublishSubject<NasaImage>) = with(itemView) {
            nasaImageView.setImageWithGlide(nasaImage.image)
            titleTextView.text = nasaImage.title
            centerAndDateTextView.setCenterAndDate(nasaImage.center, nasaImage.date.toString())
            setOnClickListener { clickSubject.onNext(nasaImage) }
        }
    }

    override fun update(list: List<NasaImage>) {
        diffCallback.compareLists(this.images, list)
        val differenceFound = DiffUtil.calculateDiff(diffCallback)
        this.images.clear()
        this.images.addAll(list)
        differenceFound.dispatchUpdatesTo(this)
    }

    override fun getClickedImage(): Observable<NasaImage> = clickSubject
}