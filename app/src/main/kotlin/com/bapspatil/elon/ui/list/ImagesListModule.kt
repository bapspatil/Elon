package com.bapspatil.elon.ui.list

import androidx.lifecycle.ViewModelProvider
import com.bapspatil.elon.api.HttpErrorUtils
import com.bapspatil.elon.ui.list.adapter.ImagesAdapter
import com.bapspatil.elon.ui.list.adapter.ImagesAdapterImpl
import com.bapspatil.elon.usecase.ImagesUseCase
import com.bapspatil.elon.util.DiffCallback
import dagger.Module
import dagger.Provides

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/


/**
 * Module for the `ImagesListActivity`
 */
@Module
class ImagesListModule {

    @Provides
    fun providesImagesListViewModelFactory(imagesUseCase: ImagesUseCase, httpErrorUtils: HttpErrorUtils): ViewModelProvider.Factory {
        return ImagesListViewModel.Factory(imagesUseCase, httpErrorUtils)
    }

    @Provides
    fun providesImagesAdapterImpl(diffCallback: DiffCallback): ImagesAdapter {
        return ImagesAdapterImpl(diffCallback)
    }

}