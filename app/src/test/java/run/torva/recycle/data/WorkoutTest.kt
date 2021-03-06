package run.torva.recycle.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit

class WorkoutTest {

    @get:Rule
    val instantExecutorRule  = InstantTaskExecutorRule()

    @Test
    fun `starting workout sets inProgress and isRunning to on`() {
        val workout = Workout()

        val statusLiveData = workout.isRunning
        val inProgressLiveData = workout.inProgress

        assertThat(statusLiveData.value).isFalse()
        assertThat(inProgressLiveData.value).isFalse()

        workout.start()

        assertThat(statusLiveData.value).isTrue()
        assertThat(inProgressLiveData.value).isTrue()

        workout.stop()
        workout.destroy()
    }

    @Test
    fun `pausing workout sets isRunning to off but keeps inProgress on`() {
        val workout = Workout()
        workout.start()

        val statusLiveData = workout.isRunning
        val inProgressLiveData = workout.inProgress

        assertThat(statusLiveData.value).isTrue()
        assertThat(inProgressLiveData.value).isTrue()

        workout.pause()
        assertThat(statusLiveData.value).isFalse()
        assertThat(inProgressLiveData.value).isTrue()

        workout.stop()
        workout.destroy()
    }

    @Test
    fun `calling pause before start throws exception`() {
        val workout = Workout()

        val exception = assertThrows(IllegalStateException::class.java) {
            workout.pause()
        }

        assertThat(exception).isInstanceOf(IllegalStateException::class.java)

        workout.destroy()
    }

    @Test
    fun `current time ticks and does not change when paused`() {
        val workout = Workout()
        workout.start()

        val onTime: Long = 2000L
        Thread.sleep(onTime)
        workout.pause()

        val current = workout.currentTime.value
        assertThat(current).isAtLeast(
            TimeUnit.SECONDS.convert(onTime, TimeUnit.MILLISECONDS)
        )

        Thread.sleep(3000L)

        // current time should not have changed
        assertThat(workout.currentTime.value).isEqualTo(current)

        workout.stop()
        workout.destroy()
    }

    @Ignore("future feature")
    @Test
    fun `elapsed time ticks when started and paused`() {
        val workout = Workout()
        workout.start()

        val onTime: Long = 2000L
        Thread.sleep(onTime)
        workout.pause()

        val elapsedLiveData = workout.elapsedTime
        assertThat(elapsedLiveData.value).isAtLeast(onTime)

        val pauseTime: Long = 3000L
        Thread.sleep(pauseTime)
        // elapsed time should keep progressing
        assertThat(workout.elapsedTime.value).isAtLeast(onTime + pauseTime)

        workout.stop()
        workout.destroy()
    }

    @Test
    fun `stopping workout sets inProgress and isRunning to false`() {
        val workout = Workout()
        workout.start()

        val statusLiveData = workout.isRunning
        val inProgressLiveData = workout.inProgress

        assertThat(statusLiveData.value).isTrue()
        assertThat(inProgressLiveData.value).isTrue()
        workout.stop()
        assertThat(statusLiveData.value).isFalse()
        assertThat(inProgressLiveData.value).isFalse()

        workout.destroy()
    }
}