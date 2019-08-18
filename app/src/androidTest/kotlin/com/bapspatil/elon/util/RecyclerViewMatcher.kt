package com.bapspatil.elon.util

import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Object used to obtain a [Matcher] for a [RecyclerView.ViewHolder.itemView] or a specific [View] inside of a [RecyclerView.ViewHolder.itemView].
 *
 * Example code to click on a button in the first row of the [RecyclerView]:
 * ```
 * onView(RecyclerViewMatcher.recyclerViewWithId(R.id.recyclerview).viewHolderViewAtPosition(0, R.id.adapter_button))
 *   .perform(click())
 * ```
 */
class RecyclerViewMatcher(@IdRes private val recyclerViewId: Int) {

    companion object {
        private const val INVALID_ID = -1

        /**
         * Receive a [RecyclerViewMatcher] with the View ID of the [RecyclerView] you want to interact with.
         */
        fun recyclerViewWithId(@IdRes viewId: Int): RecyclerViewMatcher {
            return RecyclerViewMatcher(viewId)
        }
    }

    /**
     * Receive a [Matcher] for the [RecyclerView.ViewHolder.itemView] at a certain position.
     *
     * @param index Zero based index for the row in the [RecyclerView] you want.
     *
     * @return [Matcher] you can run assertions and perform actions on.
     */
    fun itemViewAtIndex(index: Int): Matcher<View> {
        return viewHolderViewAtPosition(index, INVALID_ID)
    }

    /**
     * Receive a [Matcher] for a specific view inside of a [RecyclerView.ViewHolder] at a certain position.
     *
     * @param index Zero based index for the row in the [RecyclerView] you want.
     * @param targetViewId View id in the [RecyclerView.ViewHolder] you want to interact with.
     *
     * @return [Matcher] you can run assertions and perform actions on.
     */
    fun viewHolderViewAtPosition(index: Int, @IdRes targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var resources: Resources? = null
            var itemView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = recyclerViewId.toString()

                resources?.let { resources ->
                    idDescription = try {
                        resources.getResourceName(recyclerViewId)
                    } catch (notFound: Resources.NotFoundException) {
                        "%s (resource name not found)".format(Integer.valueOf(recyclerViewId))
                    }
                }

                description.appendText("RecyclerView view with id: $idDescription not found.")
            }

            override fun matchesSafely(view: View): Boolean {
                this.resources = view.resources

                if (itemView == null) {
                    val recyclerView: RecyclerView? = view.rootView.findViewById(recyclerViewId) as? RecyclerView
                    if (recyclerView != null && recyclerView.id == recyclerViewId) {
                        itemView = recyclerView.findViewHolderForAdapterPosition(index)!!.itemView
                    } else {
                        return false
                    }
                }

                return if (targetViewId == INVALID_ID) {
                    view === itemView
                } else {
                    val targetView = itemView!!.findViewById<View>(targetViewId)
                    view === targetView
                }

            }
        }
    }
}