package com.bapspatil.elon.ui.detail

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Module for the `ImageDetailActivity`
 */
@Module
class ImageDetailModule {

    @Provides
    fun providesImageDetailViewModelFactory(): ViewModelProvider.Factory = ImageDetailViewModel.Factory()

}