package com.bapspatil.elon.util

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bapspatil.elon.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.appbar.CollapsingToolbarLayout

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Sets an image URL to an `ImageView` with Glide
 */
fun ImageView.setImageWithGlide(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.loading_state)
        .error(R.drawable.error_state)
        .fallback(R.drawable.empty_state)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

/**
 * Sets the NASA Center and date to a `TextView`
 */
fun TextView.setCenterAndDate(center: String, date: String) {
    this.text = "$center  |  $date"
}

/**
 * Sets the navigation bar color
 */
fun Activity.setNavBarColor(@ColorRes color: Int) {
    window.navigationBarColor = ContextCompat.getColor(this, color)
}

/**
 * Sets the status bar color
 */
fun Activity.setStatusBarColor(@ColorRes color: Int) {
    window.statusBarColor = ContextCompat.getColor(this, color)
}

/**
 * Customize the CollapsingToolbarLayout
 * with custom font, title, expanded title color & collapsed title color
 */
fun CollapsingToolbarLayout.customize(
    @FontRes font: Int?,
    title: String?,
    @ColorRes expandedTitleColor: Int?,
    @ColorRes collapsedTitleColor: Int?
) {
    val typeface = font?.let { ResourcesCompat.getFont(this.context, it) }
    if (typeface != null) {
        this.setCollapsedTitleTypeface(typeface)
        this.setExpandedTitleTypeface(typeface)
    }
    if (title != null)
        this.title = title
    if (expandedTitleColor != null) {
        this.setExpandedTitleColor(ContextCompat.getColor(this.context, expandedTitleColor))
    }
    if (collapsedTitleColor != null) {
        this.setCollapsedTitleTextColor(ContextCompat.getColor(this.context, collapsedTitleColor))
    }
}