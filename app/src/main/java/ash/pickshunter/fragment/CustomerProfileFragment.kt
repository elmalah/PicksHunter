package ash.pickshunter.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import ash.pickshunter.adapter.ProductAdapter
import ash.pickshunter.model.ProductView
import ash.pickshunter.R
import ash.pickshunter.viewModel.TripViewModel
import ash.pickshunter.utils.InjectorUtils
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.viewModel.UserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_customer_profile.*
import kotlinx.android.synthetic.main.fragment_time_line.*
import kotlinx.android.synthetic.main.fragment_time_line.rv_products

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
class CustomerProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var profileCustomerId: Int? = null

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        profileCustomerId = arguments!!.getInt("profileCustomerId")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
//        swiperefresh.setOnRefreshListener {
//            loadData()
//        }

    }

    fun loadData() {
        val adapter = ProductAdapter(arrayListOf(), ::onClickListener)

//        swiperefresh.isRefreshing = true
        viewModel.getHunterProfile(PreferenceHelper(requireContext()).user.id, profileCustomerId!!)
            .observe(this) {
//            swiperefresh.isRefreshing = false

                Picasso.get().load(it.avatar)
                    .placeholder(R.drawable.placeholder).into(iv_avatar)

                tv_name.text = it.name
                tv_location.text = it.originLocation
                rating.rating = it.rating!!
                tv_number_of_followers.text = it.numberOfFollowers.toString()
                tv_number_of_trips.text = it.numberOfTrips.toString()
                tv_number_of_products.text = it.numberOfProducts.toString()

                if (it.followed == true)
                    tv_follow.text = "Un-follow"
                else
                    tv_follow.text = "Follow"


                val x = it.products!!.filter { it != null }
                rv_products.adapter = adapter
                adapter.notifyChange(x)
            }
    }

    fun onClickListener(product: ProductView, index: Int) {
        val bundle = Bundle()
        bundle.putParcelable("product", product)
        NavHostFragment.findNavController(main_navigation)
            .navigate(R.id.fragment_product_details, bundle)
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
