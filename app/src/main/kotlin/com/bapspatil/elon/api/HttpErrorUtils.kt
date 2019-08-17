package com.bapspatil.elon.api

import java.net.UnknownHostException

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Utils to check the type of HTTP error thrown
 */
class HttpErrorUtils {

    /**
     * Check if the user has lost the connection, given the throwable thrown
     * @param throwable throwable to check
     * @return true if he user has lost connection, false otherwise
     */
    fun hasLostInternet(throwable: Throwable): Boolean = throwable is UnknownHostException
}