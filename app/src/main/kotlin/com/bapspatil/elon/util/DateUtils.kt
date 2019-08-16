package com.bapspatil.elon.util

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Helper utils for LocalDate with AndroidThreeTen
 */
object DateUtils {

    /**
     * Converts a `String` date to `LocalDate`
     */
    fun getLocalDateFromString(stringDate: String): LocalDate {
        val instant = Instant.parse(stringDate)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        return localDateTime.toLocalDate()
    }

}