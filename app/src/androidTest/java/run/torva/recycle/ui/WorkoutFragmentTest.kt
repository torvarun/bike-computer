package run.torva.recycle.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import run.torva.recycle.R
import run.torva.recycle.launchFragmentInHiltContainer

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class WorkoutFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun defaultButtonTextIsStart() {
        launchFragmentInHiltContainer<WorkoutFragment>()
        onView(withId(R.id.button_startstop))
            .check(matches(withText(R.string.start_workout)))
    }

    @Test
    fun startWorkoutShouldChangeButtonToStopText() {
        launchFragmentInHiltContainer<WorkoutFragment>()
        onView(withId(R.id.button_startstop))
                .perform(click())
                .check(matches(withText(R.string.pause_workout)))
    }

    @Test
    fun pauseWorkoutShouldChangeButtonToStopText() {
        launchFragmentInHiltContainer<WorkoutFragment>()
        onView(withId(R.id.button_startstop))
                .perform(click())
                .perform(click())
                .check(matches(withText(R.string.resume_workout)))
    }
}