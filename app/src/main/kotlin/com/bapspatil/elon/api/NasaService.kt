package com.bapspatil.elon.api

import com.bapspatil.elon.api.model.NasaResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

interface NasaService {
    @GET("search?q=milky%20way&media_type=image&year_start=2017")
    fun getNasaData(@Query("q") QUERY: String?, @Query("media_type") MEDIA_TYPE: String?, @Query("year_start") YEAR_START: Int?, @Query("year_end") YEAR_END: Int?): Single<NasaResponse>
}