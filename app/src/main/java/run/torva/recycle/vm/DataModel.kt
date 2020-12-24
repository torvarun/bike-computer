package run.torva.recycle.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import run.torva.recycle.data.Stopwatch
import run.torva.recycle.data.Workout

class DataModel constructor(
    private val workout: Workout = Workout()
): ViewModel() {

    val inProgress : LiveData<Boolean> = workout.inProgress

    val status : LiveData<Boolean> = workout.isRunning

    val speed : LiveData<Double> = workout.speed

    val distance : LiveData<Double> = workout.distance

    val currentTime : LiveData<Long> = workout.currentTime

    fun start() = workout.start()

    fun pause() = workout.pause()

    fun stop() = workout.stop()

    override fun onCleared() {
        workout.destroy()
        super.onCleared()
    }
}