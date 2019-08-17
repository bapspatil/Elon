package com.bapspatil.elon.util

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Subscribes a `Single` to the IO thread and observes on the Android main thread
 */
fun <T> Single<T>.io(): Single<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}

/**
 * Subscribes a `Observable` to the IO thread and observes on the Android main thread
 */
fun <T> Observable<T>.io(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}

/**
 *   Convert ResponseBody to List of objects
 *   This method lets you convert a ResponseBody to list of objects for faster parsing of heavier JSON data
 *   Source: https://proandroiddev.com/retrofitting-and-rxjaving-heavy-jsons-2c1fcfa6383c
 */
inline fun <reified T> ResponseBody.toObjectsList(): Single<List<T>> {
    val responseBody = this
    val gson = Gson()
    return Observable.create<T> { emitter ->
        JsonReader(responseBody.charStream())
                .use { reader ->
                    while (reader.hasNext()) {
                        if (reader.peek() == JsonToken.BEGIN_ARRAY) {
                            reader.beginArray()
                            while (reader.hasNext() && reader.peek() != JsonToken.END_ARRAY) {
                                emitter.onNext(
                                        gson.fromJson<T>(
                                                reader, T::class.java
                                        )
                                )
                            }
                        }
                    }
                    emitter.onComplete()
                }
    }.toList()
}