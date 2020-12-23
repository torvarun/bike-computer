package run.torva.recycle.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * TODO
 */
class Workout constructor(
    private val stopwatch: Stopwatch = Stopwatch()
): Stopwatch.Listener {

    init {
        stopwatch.listener = this
    }

    private val _isRunning = MutableLiveData<Boolean>(false)
    val isRunning : LiveData<Boolean> = _isRunning

    private val _currentTime = MutableLiveData<Long>(0)
    val currentTime : LiveData<Long> = _currentTime

    // TODO
    private val _elapsedTime = MutableLiveData<Long>(0)
    val elapsedTime : LiveData<Long> = _elapsedTime

    private val _distance = MutableLiveData<Double>(10.33)
    val distance : LiveData<Double> = _distance

    private val _speed = MutableLiveData<Double>(24.3)
    val speed : LiveData<Double> = _speed

    fun start() {
        if (stopwatch.start()) {
            _isRunning.value = true
        }
    }

    fun pause() {
        stopwatch.pause()
        _isRunning.value = false
    }

    fun stop() {
        stopwatch.pause()
        _isRunning.value = false
        // TODO
    }

    fun destroy() {
        stopwatch.dispose()
    }

    override fun onTimeUpdated(elapsedTimeNanoseconds: Long) {
        _currentTime.value = elapsedTimeNanoseconds
    }

}