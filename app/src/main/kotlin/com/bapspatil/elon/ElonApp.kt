package com.bapspatil.elon

import android.app.Application
import com.bapspatil.elon.di.component.DaggerElonAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

class ElonApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initTimeZone()
        initDagger()
    }

    /**
     * Init time zone for AndroidThreeTen library
     */
    private fun initTimeZone() {
        AndroidThreeTen.init(this)
    }

    /**
     * Init Dagger 2 for Dependency Injection
     */
    private fun initDagger() {
        DaggerElonAppComponent.builder()
            .context(this)
            .build()
            .inject(this)
    }

    // TODO: Unit tests
    // TODO: Add art folder with icon, screenshots, color scheme and fonts
    // TODO: Update README
    // TODO: Build APK file
    // TODO: Get images with ResponseBody after parsing
    // TODO: Lot of abstraction
}