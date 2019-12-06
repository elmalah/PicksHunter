package ash.pickshunter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.*
import ash.pickshunter.adapter.TripDetailsProductAdapter
import ash.pickshunter.adapter.TripDetailsRequestAdapter
import ash.pickshunter.model.OrderView
import ash.pickshunter.model.ProductView
import ash.pickshunter.model.Trip
import ash.pickshunter.model.TripDetailsResponse
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.TripViewModel
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.fragment_trip.*
import com.google.android.material.floatingactionbutton.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_go_trip_details.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TripFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TripFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TripFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var trip: TripDetailsResponse? = null
    lateinit var tripProductsRecyclerView: RecyclerView
    lateinit var tripRequestsRecyclerView: RecyclerView


    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

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
        return inflater.inflate(R.layout.fragment_trip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fb_actions.setOnClickListener()
        {
            toggleButton(fb_add_expense)
            toggleButton(fb_add_product)
            toggleButton(fb_end_trip)
            toggleButton(fb_checkin_store)

            toggleText(text_add_expense)
            toggleText(text_add_product)
            toggleText(text_end_trip)
            toggleText(text_checkin_store)
        }

        fb_checkin_store.setOnClickListener() {
            NavHostFragment.findNavController(main_navigation).navigate(R.id.fragment_trip_stores)
        }

        fb_add_product.setOnClickListener() {
            NavHostFragment.findNavController(main_navigation).navigate(R.id.fragment_trip_stores)
        }
        tv_showAll.setOnClickListener() {
            NavHostFragment.findNavController(main_navigation).navigate(R.id.fragment_hunter_requests_list)
        }

        getTripDetails()
    }

    private fun getTripDetails() {

        ProgressDialog.show(requireContext(), false)

        viewModel.getTripDetails(
            PreferenceHelper(requireContext()).user.id,
            true,
            true,
            true,
            true
        ).observe(this) {
            ProgressDialog.dismiss()
            trip = it.getOrNull(0)

            tv_trip_title.text = trip?.to?.stateProvinceName + ", " + trip?.to?.countryName
            tv_time_remaining.text = trip?.remaining

            Picasso.get().load(trip?.flagUrl)
                .placeholder(R.drawable.placeholder).into(iv_country_flag)

            loadTripProducts()
            loadTripRequests()
        }


    }

    private fun loadTripProducts() {
        tripProductsRecyclerView = rv_trip_products as RecyclerView

        tripProductsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL, false
            )
            adapter = TripDetailsProductAdapter(trip?.products!!, ::onProductClickListener)
        }

    }

    private fun loadTripRequests(){
        // Implement Requests RecyclerView
        tripRequestsRecyclerView = rv_trip_requests as RecyclerView

        tripRequestsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.HORIZONTAL, false
            )
            adapter = TripDetailsRequestAdapter(trip?.orders!!, ::onRequestClickListener)
        }
    }

    private fun onProductClickListener(product: ProductView, index: Int) {
        val bundle = Bundle()
        bundle.putParcelable("product", product)
        NavHostFragment.findNavController(main_navigation)
            .navigate(R.id.fragment_product_details, bundle)
    }

    private fun onRequestClickListener(order: OrderView, index: Int) {
        val bundle = Bundle()
        bundle.putParcelable("order", order)
        NavHostFragment.findNavController(main_navigation)
            .navigate(R.id.fragment_hunter_requests_list, bundle)
    }

   private fun toggleButton(button: FloatingActionButton) {
        if (button.isShown()) {
            button.hide();
        } else {
            button.show();
        }
    }

    fun toggleText(textView: CardView) {

        if (textView.visibility == View.VISIBLE) {
            textView.visibility = View.INVISIBLE
        } else {
            textView.visibility = View.VISIBLE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TripFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TripFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
