package ash.pickshunter


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_trip_details.*

/**
 * A simple [Fragment] subclass.
 */
class TripDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        txt_test.text = ""
        fb_checkout.setOnClickListener(){

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_details, container, false)
    }


}
