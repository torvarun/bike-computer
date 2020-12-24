package run.torva.recycle.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule

import com.google.common.truth.Truth.assertThat;

import org.junit.Test

class DataModelTest {

    @get:Rule
    val instantExecutorRule  = InstantTaskExecutorRule()

    private lateinit var dataModel: DataModel

    @Before
    fun setUp() {
        dataModel = DataModel()
    }

    @Test
    fun `starting stopwatch sets statuses on`() {
        dataModel.start()

        assertThat(dataModel.status.value).isTrue()
        assertThat(dataModel.inProgress.value).isTrue()
    }

    @Test
    fun `pausing stopwatch sets status off but keeps progress on`() {
        dataModel.start()
        dataModel.pause()

        assertThat(dataModel.status.value).isFalse()
        assertThat(dataModel.inProgress.value).isTrue()
    }
}