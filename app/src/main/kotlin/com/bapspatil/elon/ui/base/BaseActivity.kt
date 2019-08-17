package com.bapspatil.elon.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Base Activity to handle disposables and dependency injection
 */
open class BaseActivity : AppCompatActivity() {

    protected var disposables = CompositeDisposable() // protected to avoid memory leak

    public override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this) // Dagger 2 injection
        super.onCreate(savedInstanceState)
    }

    public override fun onDestroy() {
        super.onDestroy()
        disposables.dispose() // free the memory
    }

}