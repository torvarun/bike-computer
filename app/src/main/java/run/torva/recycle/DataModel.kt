package run.torva.recycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel() {

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

    fun setStatus(isRunning : Boolean) {
        status.value = isRunning
    }
}