package com.bapspatil.elon.api

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@RunWith(MockitoJUnitRunner::class)
class HttpErrorUtilsTest {

    private val httpErrorUtils = HttpErrorUtils()

    @Test
    fun `Check if the user has lost the connection given a UnknownHostException`() {
        assertThat(httpErrorUtils.hasLostInternet(UnknownHostException())).isTrue()
    }

    @Test
    fun `Check if the user has lost the connection given an exception that is not UnknownHostException`() {
        assertThat(httpErrorUtils.hasLostInternet(RuntimeException())).isFalse()
    }

}