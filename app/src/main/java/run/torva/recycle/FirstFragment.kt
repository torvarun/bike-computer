package run.torva.recycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var speedView : TextView? = null
    var distanceView : TextView? = null
    var timeView : TextView? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        speedView = view.findViewById(R.id.textview_speed)
        distanceView = view.findViewById(R.id.textview_distance)
        timeView = view.findViewById(R.id.textview_time)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dataModel : DataModel by viewModels()

        dataModel.getSpeed().observe(viewLifecycleOwner, Observer<Double> { speed ->
            speedView?.text = speed.toString() + " kmph"
        })

        dataModel.getDistance().observe(viewLifecycleOwner, Observer<Double> { distance ->
            distanceView?.text = distance.toString()
        })

        dataModel.getTime().observe(viewLifecycleOwner, Observer<Double> { time ->
            timeView?.text = time.toString()
        })
    }
}