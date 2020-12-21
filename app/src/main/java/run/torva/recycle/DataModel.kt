package run.torva.recycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel(), Stopwatch.Listener {

    private val stopwatch : Stopwatch = Stopwatch()
    init {
        stopwatch.listener = this
    }

    private val speed : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>(24.3)
    }

    private val distance : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>(10.3332)
    }

    private val time : MutableLiveData<Long> by lazy {
        MutableLiveData<Long>(154)
    }

    // true is started, false is paused
    private val status : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getSpeed() : LiveData<Double> = speed

    fun getDistance() : LiveData<Double> = distance

    fun getTime() : LiveData<Long> = time

    fun setTime(newTime : Long) {
        time.value = newTime
    }

    fun getStatus() : LiveData<Boolean> = status

    override fun onTimeUpdated(elapsedTimeInSeconds: Long) {
        time.postValue(elapsedTimeInSeconds)
    }

    fun start() {
        if (stopwatch.start()) {
            status.postValue(true)
        }
    }

    fun pause() {
        stopwatch.pause()
        status.postValue(false)
    }

    fun reset() {
        stopwatch.reset()
    }

    override fun onCleared() {
        stopwatch.dispose()
        super.onCleared()
    }
}