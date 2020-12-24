package run.torva.recycle

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilKtTest {

    @Test
    fun `formatted time with all hours mins secs`() {
        val time: Long = (3L * 60L * 60L) +
                (38L * 60L) +
                (47L)

        val formatted = timeToFormattedString(time)

        assertThat(formatted).isEqualTo("3:38:47")
    }

    @Test
    fun `formatted time with no hours`() {
        val time: Long = (38L * 60L) +
                (47L)

        val formatted = timeToFormattedString(time)

        assertThat(formatted).isEqualTo("38:47")
    }

    @Test
    fun `formatted time with only seconds`() {
        val time: Long = 47L

        val formatted = timeToFormattedString(time)

        assertThat(formatted).isEqualTo("00:47")
    }

    @Test
    fun `formatted time with no minutes`() {
        val time: Long = (3L * 60L * 60L) +
                (47L)

        val formatted = timeToFormattedString(time)

        assertThat(formatted).isEqualTo("3:00:47")
    }

    @Test
    fun `formatted time with less than 10 seconds`() {
        val time: Long = (38L * 60L) +
                (4L)

        val formatted = timeToFormattedString(time)

        assertThat(formatted).isEqualTo("38:04")

    }
}