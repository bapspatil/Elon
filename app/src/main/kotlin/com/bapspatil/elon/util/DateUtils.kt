package com.bapspatil.elon.util

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

object DateUtils {
    fun getLocalDateFromString(stringDate: String): LocalDate {
        val instant = Instant.parse(stringDate)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        return localDateTime.toLocalDate()
    }
}