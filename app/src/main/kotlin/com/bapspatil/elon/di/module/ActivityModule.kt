package com.bapspatil.elon.di.module

import com.bapspatil.elon.ui.list.ImagesListActivity
import com.bapspatil.elon.ui.list.ImagesListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [ImagesListModule::class])
    abstract fun contributeImagesListInjector(): ImagesListActivity
}