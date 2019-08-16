package com.bapspatil.elon.di.module

import com.bapspatil.elon.usecase.ImagesUseCase
import com.bapspatil.elon.usecase.ImagesUseCaseImpl
import dagger.Binds
import dagger.Module

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Module responsible for providing use cases
 */
@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindsImagesUseCase(imagesUseCaseImpl: ImagesUseCaseImpl): ImagesUseCase

}