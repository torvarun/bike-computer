package run.torva.recycle.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule

import com.google.common.truth.Truth.assertThat;

import org.junit.Test
import run.torva.recycle.data.Workout

class WorkoutFragmentViewModelTest {

    @get:Rule
    val instantExecutorRule  = InstantTaskExecutorRule()

    private lateinit var workoutFragmentViewModel: WorkoutFragmentViewModel

    @Before
    fun setUp() {
        workoutFragmentViewModel = WorkoutFragmentViewModel(workout = Workout())
    }

    @Test
    fun `starting stopwatch sets statuses on`() {
        workoutFragmentViewModel.start()

        assertThat(workoutFragmentViewModel.status.value).isTrue()
        assertThat(workoutFragmentViewModel.inProgress.value).isTrue()
    }

    @Test
    fun `pausing stopwatch sets status off but keeps progress on`() {
        workoutFragmentViewModel.start()
        workoutFragmentViewModel.pause()

        assertThat(workoutFragmentViewModel.status.value).isFalse()
        assertThat(workoutFragmentViewModel.inProgress.value).isTrue()
    }
}