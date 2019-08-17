package com.bapspatil.elon.detail

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.bapspatil.elon.ElonApp
import com.bapspatil.elon.R
import com.bapspatil.elon.model.NasaImage
import com.bapspatil.elon.ui.detail.ImageDetailActivity
import com.bapspatil.elon.ui.detail.ImageDetailViewModel
import com.bapspatil.elon.util.DataBuilder
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Provider

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@RunWith(AndroidJUnit4::class)
class ImageDetailActivityTest {

    private val testViewModelFactory: ImageDetailViewModel.Factory = ImageDetailViewModel.Factory()

    @get:Rule
    val activityTestRule = ActivityTestRule(ImageDetailActivity::class.java, false, false)

    private fun initDispatcherAndLaunchActivity(intent: Intent? = null) {
        val myApp = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as ElonApp
        myApp.dispatchingAndroidInjector = createFakeActivityInjector {
            viewModelFactory = testViewModelFactory
        }
        activityTestRule.launchActivity(intent)
    }

    @Test
    fun showImageDetail_whenImageIsSuccessfullyPassedViaIntent() {
        val image = DataBuilder.getImageDetail()
        val intent = Intent().apply {
            putExtra(ImageDetailActivity.IMAGE_SELECTED, image)
        }
        initDispatcherAndLaunchActivity(intent)

        onView(withId(R.id.nasaImageView)).check(matches(isDisplayed()))

        onView(withId(R.id.titleTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTextView)).check(matches(withText(image.title)))

        onView(withId(R.id.centerAndDateTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.centerAndDateTextView)).check(matches(withText(
            "Center: ${image.center}\nDate Created: ${image.date}"
        )))

        onView(withId(R.id.descriptionTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.descriptionTextView)).check(matches(withText(image.description)))

        onView(withId(R.id.stateAnimationView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun showError_whenImageIsUnsuccessfullyPassedViaIntent() {
        val image: NasaImage? = null
        val intent = Intent().apply {
            putExtra(ImageDetailActivity.IMAGE_SELECTED, image)
        }
        initDispatcherAndLaunchActivity(intent)

        onView(withId(R.id.contentContainer)).check(matches(not(isDisplayed())))
        onView(withId(R.id.stateAnimationView)).check(matches(isEnabled()))
    }

    private fun createFakeActivityInjector(block: ImageDetailActivity.() -> Unit)
            : DispatchingAndroidInjector<Any> {
        val injector = AndroidInjector<Any> { instance ->
            if (instance is ImageDetailActivity) {
                instance.block()
            }
        }
        val factory = AndroidInjector.Factory<Any> { injector }
        val map = mapOf(
            Pair<Class<*>,
                    Provider<AndroidInjector.Factory<*>>>(ImageDetailActivity::class.java, Provider { factory })
        )
        return DispatchingAndroidInjector_Factory.newInstance(map, emptyMap())
    }

}