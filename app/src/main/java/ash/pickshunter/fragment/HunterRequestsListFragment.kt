package ash.pickshunter.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import ash.pickshunter.*
import ash.pickshunter.adapter.MyPagerAdapter
import ash.pickshunter.adapter.StoreAdapter
import ash.pickshunter.country.ShopActivity
import ash.pickshunter.model.Shop
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.TripViewModel
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_hunter_requests_list.*
import kotlinx.android.synthetic.main.fragment_trip_stores.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TripStoresFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TripStoresFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HunterRequestsListFragment : Fragment() {

//    lateinit var adapter: StoreAdapter

    //private var trip = Trip()
    private var tripId = 0

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        adapter = StoreAdapter(arrayListOf(), ::onClickListener)
    }

//    private fun onClickListener(shop: Shop, index: Int, addProduct: Boolean) {
//        if (addProduct) {
//            val bundle = Bundle()
//            bundle.putParcelable("shop", shop)
//            NavHostFragment.findNavController(main_navigation).navigate(R.id.fragment_hunter_requests_list, bundle)
//        }
//        else
//            checkOut(shop.tripShopId!!)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hunter_requests_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //trip = arguments!!.getParcelable("trip")!!
        tripId = PreferenceHelper(requireContext()).tripId

        val fragmentAdapter = MyPagerAdapter(childFragmentManager)
        viewpager_main.adapter = fragmentAdapter

//        tabs_main.setupWithViewPager(this.viewpager_main)
        tabs_main.setupWithViewPager(viewpager_main)
//        et_store.setOnClickListener {
//            startActivityForResult(Intent(requireContext(), ShopActivity::class.java), 102)
//        }
//        getStores()
    }

//    private fun getStores() {
//        ProgressDialog.show(requireContext(), false)
//        viewModel.getTripStores(tripId).observe(this) {
//            ProgressDialog.dismiss()
//            rv_stories.adapter = adapter
//            adapter.notifyChange(it)
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            super.onActivityResult(requestCode, resultCode, data)
//            when (requestCode) {
//                102 -> {
//                    val shop = data!!.getParcelableExtra<Shop>("shop")
//                    et_store.setText(shop.name)
//                    checkInShop(shop.id!!)
//                }
//            }
//        }
//    }

//    fun checkInShop(storeId: Int) {
//        ProgressDialog.show(requireContext(), false)
//        viewModel.checkIn(tripId, storeId).observe(this) {
//            getStores()
//        }
//    }


//    fun checkOut(storeId: Int) {
//        ProgressDialog.show(requireContext(), false)
//        viewModel.checkOut(storeId).observe(this) {
//            getStores()
//        }
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TripStoresFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TripStoresFragment().apply {

            }
    }
}
