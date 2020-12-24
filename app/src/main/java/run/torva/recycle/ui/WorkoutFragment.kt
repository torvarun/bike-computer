package run.torva.recycle.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import run.torva.recycle.R
import run.torva.recycle.timeToFormattedString
import run.torva.recycle.vm.DataModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WorkoutFragment : Fragment() {

    private val dataModel : DataModel by viewModels()

    private var speedView : TextView? = null
    private var distanceView : TextView? = null
    private var timeView : TextView? = null
    private var buttonStartStop : Button? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        speedView = view.findViewById(R.id.textview_speed)
        distanceView = view.findViewById(R.id.textview_distance)
        buttonStartStop = view.findViewById(R.id.button_startstop)
        timeView = view.findViewById(R.id.textview_time)

        dataModel.speed.observe(viewLifecycleOwner, Observer<Double> { speed ->
            speedView?.text = speed.toString()
        })

        dataModel.distance.observe(viewLifecycleOwner, Observer<Double> { distance ->
            distanceView?.text = distance.toString()
        })

        buttonStartStop?.setOnClickListener{
            val isRunning : Boolean = dataModel.status.value ?: false

            if (isRunning) {
                // this button click pauses activity
                stop()
            } else {
                // button click starts activity
                start()
            }
        }

        dataModel.status.observe(viewLifecycleOwner, Observer<Boolean>{ isRunning ->
            if (isRunning) {
                buttonStartStop?.setText(R.string.stop)
            } else {
                buttonStartStop?.setText(R.string.start)
            }
        })

        dataModel.currentTime.observe(viewLifecycleOwner, Observer<Long>{ time ->
            timeView?.text = timeToFormattedString(time)
        })
    }

    private fun start() {
        Timber.d("starting workout")
        dataModel.start()
    }

    private fun stop() {
        Timber.d("stopping workout")
        dataModel.stop()
    }
}