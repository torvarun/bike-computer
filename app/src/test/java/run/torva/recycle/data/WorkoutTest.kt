package run.torva.recycle.data

import com.google.common.truth.Truth.assertThat
import org.junit.Ignore
import org.junit.Test

class WorkoutTest {

    @Test
    fun start() {
        val workout = Workout()

        assertThat(workout.isRunning).isFalse()
        workout.start()
        assertThat(workout.isRunning).isTrue()

        workout.stop()
        workout.destroy()
    }

    @Test
    fun pause() {
        val workout = Workout()
        workout.start()

        assertThat(workout.isRunning).isTrue()
        workout.pause()
        assertThat(workout.isRunning).isFalse()

        workout.stop()
        workout.destroy()
    }

    @Test
    fun `current time ticks and does not change when paused`() {
        val workout = Workout()
        workout.start()

        val onTime: Long = 2000L
        Thread.sleep(onTime)
        workout.pause()

        val current: Long = workout.currentTime
        assertThat(current).isAtLeast(onTime)

        Thread.sleep(3000L)

        // current time should not have changed
        assertThat(workout.currentTime).isEqualTo(current)

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

        val elapsed = workout.elapsedTime
        assertThat(elapsed).isAtLeast(onTime)

        val delay: Long = 3000L
        Thread.sleep(delay)
        // elapsed time should keep progressing
        assertThat(workout.elapsedTime - elapsed).isAtLeast(delay)

        workout.stop()
        workout.destroy()
    }

    @Test
    fun stop() {
        val workout = Workout()

        workout.start()
        assertThat(workout.isRunning).isTrue()
        workout.stop()
        assertThat(workout.isRunning).isFalse()
        workout.destroy()
    }
}