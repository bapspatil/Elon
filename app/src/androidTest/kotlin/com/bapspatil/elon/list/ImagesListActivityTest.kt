package com.bapspatil.elon.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.bapspatil.elon.ElonApp
import com.bapspatil.elon.R
import com.bapspatil.elon.api.HttpErrorUtils
import com.bapspatil.elon.ui.list.ImagesListActivity
import com.bapspatil.elon.ui.list.ImagesListViewModel
import com.bapspatil.elon.ui.list.adapter.ImagesAdapter
import com.bapspatil.elon.ui.list.adapter.ImagesAdapterImpl
import com.bapspatil.elon.usecase.ImagesUseCase
import com.bapspatil.elon.util.CustomAssertions.Companion.hasItemCount
import com.bapspatil.elon.util.DataBuilder
import com.bapspatil.elon.util.DiffCallback
import com.bapspatil.elon.util.RecyclerViewMatcher
import com.bapspatil.elon.util.RecyclerViewMatcher.Companion.recyclerViewWithId
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import io.reactivex.Single
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.UnknownHostException
import javax.inject.Provider

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@RunWith(AndroidJUnit4::class)
class ImagesListActivityTest {

    private val imagesUseCase: ImagesUseCase = mock()
    private val testImagesAdapter: ImagesAdapter = ImagesAdapterImpl(DiffCallback())
    private val testViewModelFactory: ImagesListViewModel.Factory = ImagesListViewModel.Factory(imagesUseCase, HttpErrorUtils())

    @get:Rule
    val activityTestRule = ActivityTestRule(ImagesListActivity::class.java, false, false)

    private fun initDispatcherAndLaunchActivity() {
        val myApp = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as ElonApp
        myApp.dispatchingAndroidInjector = createFakeActivityInjector {
            viewModelFactory = testViewModelFactory
            imagesAdapter = testImagesAdapter
        }
        activityTestRule.launchActivity(null)
    }

    @Test
    fun displayImagesList_whenFetchingImagesIsSuccessful() {
        val images = DataBuilder.getImages()
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.just(DataBuilder.getImages()))
        initDispatcherAndLaunchActivity()

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(hasItemCount(images.size))

        val recyclerViewMatcher: RecyclerViewMatcher = recyclerViewWithId(R.id.recyclerView)
        onView(recyclerViewMatcher.viewHolderViewAtPosition(0, R.id.nasaImageView)).check(matches(isDisplayed()))
        onView(recyclerViewMatcher.viewHolderViewAtPosition(0, R.id.titleTextView)).check(matches(withText(images[0].title)))
        onView(recyclerViewMatcher.viewHolderViewAtPosition(0, R.id.titleTextView)).check(matches(isDisplayed()))
        onView(recyclerViewMatcher.viewHolderViewAtPosition(0, R.id.centerAndDateTextView)).check(matches(withText("${images[0].center}  |  ${images[0].date}")))
        onView(recyclerViewMatcher.viewHolderViewAtPosition(0, R.id.centerAndDateTextView)).check(matches(isDisplayed()))

        onView(withId(R.id.stateAnimationView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun displayError_whenFetchingImagesIsUnsuccessful() {
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.error(UnknownHostException()))
        initDispatcherAndLaunchActivity()

        onView(withId(R.id.stateAnimationView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun displayNoInternet_whenFetchingImagesIsUnsuccessful() {
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.error(Throwable("Error")))
        initDispatcherAndLaunchActivity()

        onView(withId(R.id.stateAnimationView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun displayEmptyList_whenFetchingImagesIsUnsuccessful() {
        whenever(imagesUseCase.getImages(DataBuilder.DEFAULT_QUERY, DataBuilder.DEFAULT_MEDIA_TYPE, DataBuilder.DEFAULT_YEAR_START, DataBuilder.DEFAULT_YEAR_END)).thenReturn(Single.just(emptyList()))
        initDispatcherAndLaunchActivity()

        onView(withId(R.id.stateAnimationView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerView)).check(hasItemCount(0))
    }

    private fun createFakeActivityInjector(block: ImagesListActivity.() -> Unit)
            : DispatchingAndroidInjector<Any> {
        val injector = AndroidInjector<Any> { instance ->
            if (instance is ImagesListActivity) {
                instance.block()
            }
        }
        val factory = AndroidInjector.Factory<Any> { injector }
        val map = mapOf(
                Pair<Class<*>,
                        Provider<AndroidInjector.Factory<*>>>(ImagesListActivity::class.java, Provider { factory })
        )
        return DispatchingAndroidInjector_Factory.newInstance(map, emptyMap())
    }

}