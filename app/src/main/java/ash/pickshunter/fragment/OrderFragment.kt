package ash.pickshunter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import ash.pickshunter.R
import ash.pickshunter.adapter.OrderAdapter
import ash.pickshunter.adapter.RequestAdapter

import ash.pickshunter.fragment.dummy.DummyContent
import ash.pickshunter.fragment.dummy.DummyContent.DummyItem
import ash.pickshunter.model.OrderView
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.viewModel.TripViewModel
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.fragment_order_list.*
import kotlinx.android.synthetic.main.fragment_requests_list_tab.*
import kotlinx.android.synthetic.main.fragment_requests_list_tab.swiperefresh_requests

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [OrderFragment.OnListFragmentInteractionListener] interface.
 */
class OrderFragment : Fragment() {

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order_list, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        swiperefresh_requests.setOnRefreshListener {
            loadData()
        }
    }

    fun loadData() {
        val adapter = OrderAdapter(arrayListOf(), listener)
        swiperefresh_requests.isRefreshing = true

        viewModel.getCustomerOrders(PreferenceHelper(requireContext()).user.id).observe(this) {
            swiperefresh_requests.isRefreshing = false

            rv_orders.adapter = adapter
            adapter.notifyChange(it)
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: OrderView?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
