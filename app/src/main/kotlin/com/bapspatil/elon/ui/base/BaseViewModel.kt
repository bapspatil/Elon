package com.bapspatil.elon.ui.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Base ViewModel to manage the disposables
 */
open class BaseViewModel<T> : ViewModel(), DefaultLifecycleObserver {
    protected val disposables = CompositeDisposable()
    private val viewStateRelay = BehaviorRelay.create<T>()
    open val viewStateObservable: Observable<T> = viewStateRelay.hide()

    protected fun emitViewState(viewState: T) {
        viewStateRelay.accept(viewState)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}