package run.torva.recycle.data

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * Custom stopwatch. Internal time in nanoseconds, listener reports in seconds.
 *
 * TODO switch this to coroutines
 */
class Stopwatch {

    interface Listener {
        fun onTimeUpdated(elapsedTimeInSeconds : Long)
    }

    private val execService : ScheduledExecutorService = Executors.newScheduledThreadPool(1)

    private var elapsedTime : Long = 0

    private var startTime : Long = 0

    private var isStarted : Boolean = false

    @Volatile var listener : Listener? = null

    private var scheduledFuture : ScheduledFuture<*>? = null

    @Synchronized fun start() : Boolean {
        if (isStarted) {
            return false;
        }

        isStarted = true
        startTime = System.nanoTime()

        updateListener(elapsedTime)

        startTimer()

        return true
    }

    private fun updateListener(nanoTime : Long) {
        val seconds = TimeUnit.SECONDS.convert(nanoTime, TimeUnit.NANOSECONDS)
        listener?.onTimeUpdated(seconds)
    }

    private fun startTimer() {
        scheduledFuture = execService.scheduleAtFixedRate(object: Runnable {
             override fun run() {
                synchronized (this@Stopwatch){
                    val currentTime: Long = System.nanoTime()
                    val delta: Long = currentTime - startTime
                    elapsedTime += delta
                    startTime = currentTime
                }

                updateListener(elapsedTime)
            }
        }, 0, 100, TimeUnit.MILLISECONDS)
    }

    @Synchronized fun pause() {
        isStarted = false
        startTime = 0

        scheduledFuture?.cancel(false)
        scheduledFuture = null
    }

    @Synchronized fun reset() {
        elapsedTime = 0
        startTime = System.nanoTime()
        updateListener(0)
    }

    fun dispose() {
        execService.shutdown()
    }
}