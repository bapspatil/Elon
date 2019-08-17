package com.bapspatil.elon.di.module

import com.bapspatil.elon.util.DiffCallback
import dagger.Module
import dagger.Provides

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Module responsible for providing utilities
 */
@Module
class UtilModule {

    @Provides
    fun providesDiffCallbackModule(): DiffCallback = DiffCallback()

}