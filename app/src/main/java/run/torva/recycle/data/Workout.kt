package run.torva.recycle.data

/**
 * TODO
 */
class Workout : Stopwatch.Listener {

    private val stopwatch : Stopwatch = Stopwatch()
    init {
        stopwatch.listener = this
    }

    var currentTime : Long = 0
        private set

    // TODO
    var elapsedTime : Long = 0
        private set

    var isRunning : Boolean = false
        private set

    var distance : Double = 10.33
        private set

    var speed : Double = 24.3
        private set

    fun start() {
        if (stopwatch.start()) {
            isRunning = true
        }
    }

    fun pause() {
        stopwatch.pause()
        isRunning = false
    }

    fun stop() {
        stopwatch.pause()
        isRunning = false
        // TODO
    }

    fun destroy() {
        stopwatch.dispose()
    }

    override fun onTimeUpdated(elapsedTimeNanoseconds: Long) {
        currentTime = elapsedTimeNanoseconds
    }

}