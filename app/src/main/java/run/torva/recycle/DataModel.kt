package run.torva.recycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel() {

    private val speed : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>(0.0001)
    }

    private val distance : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>(10.3332)
    }

    private val time : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>(1.54)
    }

    fun getSpeed() : LiveData<Double> {
        return speed
    }

    fun getDistance() : LiveData<Double> {
        return distance
    }

    fun getTime() : LiveData<Double> {
        return time
    }
}