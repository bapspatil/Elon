package com.bapspatil.elon

import android.app.Application
import com.bapspatil.elon.di.component.DaggerElonAppComponent
import com.bapspatil.elon.di.component.ElonAppComponent
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
        initDagger()
    }

    private fun initDagger() {
        DaggerElonAppComponent.builder()
            .context(this)
            .build()
            .also { elonAppComponent = it }
        elonAppComponent.inject(this)
    }

    companion object {
        @JvmStatic
        lateinit var elonAppComponent: ElonAppComponent
    }
}