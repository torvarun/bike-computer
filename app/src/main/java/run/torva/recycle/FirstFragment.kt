package run.torva.recycle

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

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val TAG : String = "FirstFragment"

    private val dataModel : DataModel by viewModels()

    private var speedView : TextView? = null
    private var distanceView : TextView? = null
    private var timeChrono : Stopwatch? = null
    private var buttonStartStop : Button? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        speedView = view.findViewById(R.id.textview_speed)
        distanceView = view.findViewById(R.id.textview_distance)
        timeChrono = view.findViewById(R.id.chrono_time)
        buttonStartStop = view.findViewById(R.id.button_startstop)

        dataModel.getSpeed().observe(viewLifecycleOwner, Observer<Double> { speed ->
            speedView?.text = speed.toString()
        })

        dataModel.getDistance().observe(viewLifecycleOwner, Observer<Double> { distance ->
            distanceView?.text = distance.toString()
        })

        buttonStartStop?.setOnClickListener{
            val state : Boolean = dataModel.getStatus() ?: false

            if (state) {
                // this button click pauses activity
                stop()
            } else {
                // button click starts activity
                start()
            }
        }

        timeChrono?.setOnChronometerTickListener {
            val stopwatch : Stopwatch = it as Stopwatch
            dataModel.setTime(stopwatch.stoppedTime)
        }
    }

    private fun start() {
        Log.v(TAG, "starting activity")
        dataModel.setStatus(true)
        timeChrono?.start()
        buttonStartStop?.setText(R.string.stop)
    }

    private fun stop() {
        Log.v(TAG, "stopping activity")
        dataModel.setStatus(false)
        timeChrono?.stop()
        buttonStartStop?.setText(R.string.start)
    }
}