package com.andela.d2_news_application.ui

import android.app.PendingIntent.getActivity
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import com.andela.d2_news_application.R
import com.andela.d2_news_application.ui.fashion.FashionFragment
import com.andela.d2_news_application.ui.food.FoodFragment
import com.andela.d2_news_application.ui.home.HomeFragment
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityTestRule = IntentsTestRule(MainActivity::class.java)


    @Before
    fun init() {
        activityTestRule.activity.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, HomeFragment.newInstance())
                .commit()
    }

    @Test
    fun homeActivityDisplayedProperly() {
        Espresso.onView(ViewMatchers.withId(R.id.home_container)).check(ViewAssertions.matches(
                CoreMatchers.allOf(ViewMatchers.isDisplayed())))
    }

    @Test
    fun food_fragment_should_be_lauched_when_toolbar_is_clicked() {
        onView(withId(R.id.navigation_dashboard)).perform(click())
        assertTrue(activityTestRule.activity.supportFragmentManager.findFragmentById(R.id.frame_container) is FoodFragment)
    }

    @Test
    fun fashion_fragment_should_be_lauched_when_toolbar_is_clicked() {
        onView(withId(R.id.navigation_notifications)).perform(click())
        assertTrue(activityTestRule.activity.supportFragmentManager.findFragmentById(R.id.frame_container) is FashionFragment)
    }

    @Test
    fun home_fragment_should_be_lauched_when_toolbar_is_clicked() {
        onView(withId(R.id.navigation_home)).perform(click())
        assertTrue(activityTestRule.activity.supportFragmentManager.findFragmentById(R.id.frame_container) is HomeFragment)
    }
}