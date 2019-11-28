package ash.pickshunter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_go_trip_details.*
import kotlinx.android.synthetic.main.fragment_new_product_step_one.*
import kotlinx.android.synthetic.main.fragment_plan_trip.*


class GoToTripDetailsFragment : Fragment() {

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_go_trip_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_go_to_trip.setOnClickListener {
//            NavHostFragment.findNavController(navigation_trip).navigate(R.id.fragment_add_trip)
        }

        getTripDetails()
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlanTripFragment().apply {}
    }

    fun getTripDetails() {

        ProgressDialog.show(requireContext(), false)
        viewModel.getTripDetails(PreferenceHelper(requireContext()).user.id,"true").observe(this) {
            ProgressDialog.dismiss()
            state_name.text= it[0]?.from?.stateProvinceName +","+ it[0]?.to?.stateProvinceName
//            val options = it.

        }

    }
}
