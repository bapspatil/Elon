package com.bapspatil.elon.util

import org.threeten.bp.*

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

object DateUtils {
    fun getLocalDateFromString(stringDate: String): LocalDate {
        val instant = Instant.parse(stringDate)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.id))
        return localDateTime.toLocalDate()
    }
}