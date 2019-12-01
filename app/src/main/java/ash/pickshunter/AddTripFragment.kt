package ash.pickshunter

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import ash.pickshunter.country.CountryActivity
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_trip.*
import kotlinx.android.synthetic.main.webview_layout.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddTripFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddTripFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTripFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    val trip: Trip = Trip()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_trip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trip.customerId = PreferenceHelper(requireContext()).user.id
        et_start_date.setOnClickListener {
            pickDate(startDateView = true)
        }

        et_end_date.setOnClickListener {
            pickDate(false)
        }

        et_from.setOnClickListener {
            startActivityForResult(Intent(requireActivity(), CountryActivity::class.java), 100)
        }

        et_to.setOnClickListener {
            startActivityForResult(Intent(requireActivity(), CountryActivity::class.java), 200)
        }

        cb_terms.setOnCheckedChangeListener { buttonView, isChecked ->
            bt_add_trip.isEnabled = cb_terms.isChecked
        }

        tv_termsandconditions.setOnClickListener {

            var dialog = Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.webview_layout);
            var wv = dialog.findViewById<WebView>(R.id.webView)
            wv.minimumHeight = 300
            wv.minimumWidth = 300

            wv.loadUrl("http://pickshunter-dev1.halfhardy.com/t-popup/ConditionsOfUse")
            wv.setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            })

            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.getWindow().getAttributes())
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT
            dialog.show()
            dialog.getWindow().setAttributes(lp);

            dialog.setOnKeyListener { v, actionId, event ->
                if (actionId == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss()
                    true
                } else {
                    false
                }
            }

        }

        bt_add_trip.setOnClickListener {
            if (et_from.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter Country", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (et_to.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter Country", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (et_start_date.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter Date", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (et_end_date.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter Date", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val tripRequest = TripRequest()
            tripRequest.trip = trip
            ProgressDialog.show(requireContext(), false)
            viewModel.createTrip(tripRequest).observe(this) {
                ProgressDialog.dismiss()

                if (it!!.id != null) {
                    val bundle = Bundle()
                    bundle.putParcelable("trip", it)
                    NavHostFragment.findNavController(main_navigation)
                        .navigate(R.id.fragment_go_to_trip_details, bundle)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Trip date is conflicting with other trip",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                100 -> {
                    val state = data!!.getParcelableExtra<State>("state")
                    et_from.setText(state.name)
                    trip.fromStateProvinceId = state.id
                }
                200 -> {
                    val state = data!!.getParcelableExtra<State>("state")
                    et_to.setText(state.name)
                    trip.toStateProvinceId = state.id
                }
            }
        }
    }

    private fun pickDate(startDateView: Boolean) {
        val newCalendar = Calendar.getInstance()
        val mDatePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                val sd = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
                val startDate = newDate.time
                val dateFormatted = sd.format(startDate)
                if (startDateView) {
                    et_start_date.setText(dateFormatted)
                    trip.fromDate = et_start_date.text.toString()

                } else {
                    et_end_date.setText(dateFormatted)
                    trip.toDate = et_end_date.text.toString()
                }

            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        if (startDateView)
            mDatePickerDialog.datePicker.minDate = System.currentTimeMillis()
        else {
            mDatePickerDialog.datePicker.minDate = System.currentTimeMillis()
        }

        mDatePickerDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddTripFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTripFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
