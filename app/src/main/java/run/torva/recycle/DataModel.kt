package run.torva.recycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel() {

    private val speed : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>(0.0001)
    }

    fun getSpeed() : LiveData<Double> {
        return speed
    }
}