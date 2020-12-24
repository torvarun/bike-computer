package run.torva.recycle.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import run.torva.recycle.R

@RunWith(AndroidJUnit4::class)
class WorkoutFragmentTest {

    @Test
    fun defaultButtonTextIsStart() {
        launchFragmentInContainer<WorkoutFragment>()
        onView(withId(R.id.button_startstop))
            .check(matches(withText(R.string.start_workout)))
    }

    @Test
    fun startWorkoutShouldChangeButtonToStopText() {
        launchFragmentInContainer<WorkoutFragment>()
        onView(withId(R.id.button_startstop))
                .perform(click())
                .check(matches(withText(R.string.pause_workout)))
    }

    @Test
    fun pauseWorkoutShouldChangeButtonToStopText() {
        launchFragmentInContainer<WorkoutFragment>()
        onView(withId(R.id.button_startstop))
                .perform(click())
                .perform(click())
                .check(matches(withText(R.string.resume_workout)))
    }
}