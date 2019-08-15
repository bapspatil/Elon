package com.bapspatil.elon.di.module

import com.bapspatil.elon.repo.ImagesRepository
import com.bapspatil.elon.repo.ImagesRepositoryImpl
import dagger.Binds
import dagger.Module

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsImagesRepository(imagesRepositoryImpl: ImagesRepositoryImpl): ImagesRepository

}
