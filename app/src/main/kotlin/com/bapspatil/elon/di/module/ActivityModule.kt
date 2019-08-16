package com.bapspatil.elon.di.module

import com.bapspatil.elon.ui.detail.ImageDetailActivity
import com.bapspatil.elon.ui.detail.ImageDetailModule
import com.bapspatil.elon.ui.list.ImagesListActivity
import com.bapspatil.elon.ui.list.ImagesListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

/**
 * Module that is responsible for injection in Activities
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [ImagesListModule::class])
    abstract fun contributeImagesListInjector(): ImagesListActivity

    @ContributesAndroidInjector(modules = [ImageDetailModule::class])
    abstract fun contributeImageDetailInjector(): ImageDetailActivity

}