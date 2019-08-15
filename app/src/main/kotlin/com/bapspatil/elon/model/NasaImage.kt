package com.bapspatil.elon.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDate

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@Parcelize
data class NasaImage(
    val image: String,
    val title: String,
    val description: String,
    val center: String,
    val date: LocalDate
) : Parcelable