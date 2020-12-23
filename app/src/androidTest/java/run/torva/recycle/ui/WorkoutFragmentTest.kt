package run.torva.recycle.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import run.torva.recycle.R

@RunWith(AndroidJUnit4::class)
class WorkoutFragmentTest {

    @Test
    fun startWorkoutShouldChangeButtonToStopText() {
        launchFragmentInContainer<WorkoutFragment>()
        onView(withId(R.id.button_startstop))
                .perform(click())
                .check(matches(withText(R.string.stop)))
    }

    @Test
    fun stopWorkoutShouldChangeButtonToStopText() {
        launchFragmentInContainer<WorkoutFragment>()
        onView(withId(R.id.button_startstop))
                .perform(click())
                .perform(click())
                .check(matches(withText(R.string.start)))
    }
}