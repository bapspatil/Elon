package com.bapspatil.elon.util

import android.widget.ImageView
import android.widget.TextView
import com.bapspatil.elon.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

fun ImageView.setImageWithGlide(url: String) {
    Glide.with(this)
            .load(url)
            .placeholder(R.drawable.loading_state)
            .error(R.drawable.error_state)
            .fallback(R.drawable.error_state)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}

fun TextView.setCenterAndDate(center: String, date: String) {
    this.text = "$center  |  $date"
}