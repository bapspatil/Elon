package com.bapspatil.elon.api

import com.bapspatil.elon.api.model.NasaResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * NASA service used to access the REST API to get images and data
 */
interface NasaService {

    /**
     * GET request to the Search API to get images and data from the NASA serzice
     */
    @GET("search")
    fun getNasaData(
            @Query("q") QUERY: String?,
            @Query("media_type") MEDIA_TYPE: String?,
            @Query("year_start") YEAR_START: Int?,
            @Query("year_end") YEAR_END: Int?
    ): Single<NasaResponse>

}