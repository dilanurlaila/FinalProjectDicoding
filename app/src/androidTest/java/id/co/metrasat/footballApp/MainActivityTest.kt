package id.co.metrasat.footballApp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var mActivityTestRule = ActivityTestRule(Main2Activity::class.java)

    @Test
    fun main2ActivityTest() {
        val recyclerView = onView(
                Matchers.allOf(withId(R.id.rvEventPast),
                        childAtPosition(
                                withClassName(`is`("android.widget.RelativeLayout")),
                                2)))
        recyclerView.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(2000)
        val actionMenuItemView = onView(
                Matchers.allOf(withId(R.id.add_to_favorite), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        Thread.sleep(2000)

        pressBack()

        Thread.sleep(2000)
        val bottomNavigationItemView = onView(
                Matchers.allOf(withId(R.id.next_match),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        Thread.sleep(2000)
        val bottomNavigationItemView2 = onView(
                Matchers.allOf(withId(R.id.favorite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        Thread.sleep(2000)
        val recyclerView2 = onView(
                Matchers.allOf(withId(R.id.rvFavorite),
                        childAtPosition(
                                withClassName(`is`("android.widget.RelativeLayout")),
                                2)))
        recyclerView2.perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(3000)
        val actionMenuItemView2 = onView(
                Matchers.allOf(withId(R.id.add_to_favorite), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

        Thread.sleep(3000)
        val appCompatImageButton2 = onView(
                Matchers.allOf(withContentDescription("Navigasi naik"),
                        childAtPosition(
                                Matchers.allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton2.perform(click())

        Thread.sleep(3000)
        pressBack()

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
