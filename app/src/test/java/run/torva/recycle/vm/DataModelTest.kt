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
    fun `starting stopwatch sets status on`() {
        dataModel.start()

        val status = dataModel.status.value ?: false
        assertThat(status).isTrue()
    }

    @Test
    fun `pausing stopwatch sets status off`() {
        dataModel.stop()

        val status = dataModel.status.value ?: false
        assertThat(status).isFalse()
    }
}