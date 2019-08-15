package com.bapspatil.elon.di.module

import android.content.Context
import com.bapspatil.elon.BuildConfig
import com.bapspatil.elon.api.HttpErrorUtils
import com.bapspatil.elon.api.NasaService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@Module
class NetworkModule {

    @Provides
    fun createCache(context: Context): Cache {
        val cacheDir = File(context.cacheDir, cacheName)
        return Cache(cacheDir, cacheSize)
    }

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // responsible to create the cache interceptor. Simple one at the moment but could be improved
    // to cache differently per resource
    @Provides
    fun provideNetworkCacheInterceptor() = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
                .maxAge(cacheMaxAgeMinute, TimeUnit.MINUTES)
                .build()

        response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
    }

    @Provides
    fun createOkHttpClient(
            networkInterceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
            addInterceptor(networkInterceptor)
            addInterceptor { chain ->
                val request = chain
                        .request()
                        .newBuilder()

                chain.proceed(request.build())
            }
            readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        }.build()
    }


    @Provides
    @Singleton
    fun createRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.PROD_BASE_URL)
            client(client)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    fun createHttpErrorUtils() = HttpErrorUtils()

    @Provides
    fun createNasaService(retrofit: Retrofit): NasaService = retrofit.create(NasaService::class.java)

    companion object {
        private const val TIMEOUT_SEC = 20L
        private const val cacheMaxAgeMinute = 10
        private const val cacheSize = 1024 * 1024 * 10L // 10 MB
        private const val cacheName = "cache_name"
    }

}