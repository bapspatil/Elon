package com.bapspatil.elon.di

import android.app.Instrumentation
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.bapspatil.elon.ElonApp

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/
class EspressoTestRunner: AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?)
            = Instrumentation.newApplication(ElonApp::class.java, context)

}