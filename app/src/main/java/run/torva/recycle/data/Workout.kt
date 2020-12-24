package run.torva.recycle.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit

/**
 * TODO
 */
class Workout constructor(
    private val stopwatch: Stopwatch = Stopwatch()
): Stopwatch.Listener {

    init {
        stopwatch.listener = this
    }

    private val _inProgress = MutableLiveData<Boolean>(false)
    val inProgress : LiveData<Boolean> = _inProgress

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
            _inProgress.value = true
            _isRunning.value = true
        }
    }

    @Throws(IllegalStateException::class)
    fun pause() {
        if (_inProgress.value != true) {
            throw IllegalStateException("Cannot call pause when workout is not in progress")
        }

        stopwatch.pause()
        _isRunning.value = false
    }

    fun stop() {
        stopwatch.pause()
        _inProgress.value = false
        _isRunning.value = false
        // TODO
    }

    fun destroy() {
        stopwatch.dispose()
    }

    override fun onTimeUpdated(elapsedTimeNanoseconds: Long) {
        _currentTime.postValue(
            TimeUnit.SECONDS.convert(elapsedTimeNanoseconds, TimeUnit.NANOSECONDS)
        )
    }

}