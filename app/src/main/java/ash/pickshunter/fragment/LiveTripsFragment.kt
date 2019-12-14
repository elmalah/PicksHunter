//package ash.pickshunter.fragment
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.cardview.widget.CardView
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.observe
//import androidx.navigation.fragment.NavHostFragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import ash.pickshunter.*
//import ash.pickshunter.adapter.TripDetailsProductAdapter
//import ash.pickshunter.adapter.TripDetailsRequestAdapter
//import ash.pickshunter.model.OrderView
//import ash.pickshunter.model.ProductView
//import ash.pickshunter.model.TripDetailsResponse
//import ash.pickshunter.utils.PreferenceHelper
//import ash.pickshunter.utils.ProgressDialog
//import ash.pickshunter.viewModel.TripViewModel
//import ash.pickshunter.utils.InjectorUtils
//import kotlinx.android.synthetic.main.fragment_trip.*
//import com.google.android.material.floatingactionbutton.*
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.activity_main.*
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
//
///**
// * A simple [Fragment] subclass.
// * Activities that contain this fragment must implement the
// * [TripFragment.OnFragmentInteractionListener] interface
// * to handle interaction events.
// * Use the [TripFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class LiveTripsFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    private var trip: TripDetailsResponse? = null
//    lateinit var tripProductsRecyclerView: RecyclerView
//    lateinit var tripRequestsRecyclerView: RecyclerView
//
//
//    private val viewModel: TripViewModel by viewModels {
//        InjectorUtils.provideTripViewModelFactory(requireContext())
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_live_trips, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//
////        getTripDetails()
//    }
//
//
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment TripFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            TripFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}
package ash.pickshunter.fragment

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import ash.pickshunter.R
import ash.pickshunter.adapter.LiveTripsAdapter
import ash.pickshunter.adapter.RequestAdapter
import ash.pickshunter.model.OrderView
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.TripViewModel
import ash.pickshunter.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_requests_list_tab.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TimeLineFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TimeLineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LiveTripsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var orderStatusId = 3

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
        return inflater.inflate(R.layout.fragment_live_trips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        swiperefresh_requests.setOnRefreshListener {
            loadData()
        }
    }

    fun loadData() {
        val adapter = LiveTripsAdapter(arrayListOf(), ::onClickListener)
        swiperefresh_requests.isRefreshing = true

        viewModel.getTripDetails(
            PreferenceHelper(requireContext()).user.id,
            true,
            false,
            false,
            true
        ).observe(this) {
            swiperefresh_requests.isRefreshing = false

            rv_requests.adapter = adapter
            adapter.notifyChange(it.getOrNull(0)!!.orders!!.filter {
                it.orderStatusId == orderStatusId
            })
        }
    }

    fun onClickListener(order: OrderView, index: Int, done: Boolean) {
        val bundle = Bundle()
        bundle.putParcelable("order", order)

        if (done) {
            val dialogClickListener =
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            setOrderStatus(order.orderId, 2)
                        }
                    }
                }

            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Are you sure?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show()
        } else {
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.setTitle("Rejection Reason")
                    .setItems(R.array.rejectionReasons,
                        DialogInterface.OnClickListener { dialog, which ->
                            // The 'which' argument contains the index position of the selected item

                            var rejectionReasons = ArrayList<Int>()
                            rejectionReasons.add(1)//Item not available
                            rejectionReasons.add(2)//Offer ended
                            rejectionReasons.add(3)//Hunter not available

                            setOrderStatus(order.orderId, 4, rejectionReasons[which])
                        })
                builder.create()
            }
            alertDialog!!.show()
        }

    }

    fun setOrderStatus(orderId: Int?, orderStatusId: Int?, orderRejectionReasonId: Int? = null) {
        ProgressDialog.show(requireContext(), false)

        viewModel.updateOrderStatus(
            orderId,
            orderStatusId,
            orderRejectionReasonId
        ).observe(this) {
            ProgressDialog.dismiss()
            loadData()
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TimeLineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimeLineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
