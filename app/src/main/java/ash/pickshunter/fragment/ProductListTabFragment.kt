package ash.pickshunter.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment

import ash.pickshunter.R
import ash.pickshunter.adapter.ProductAdapter
import ash.pickshunter.model.ProductView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product_list_tab.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductListTabFragment(
    val onRefreshListener: () -> Unit
) : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var adapter: ProductAdapter? = null


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
        return inflater.inflate(R.layout.fragment_product_list_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter(arrayListOf(), ::onClickListener)
        rv_products.adapter = adapter

        swiperefresh.setOnRefreshListener {
            onRefreshListener.invoke()
        }

    }

    fun showLoader(){
        swiperefresh?.isRefreshing = true
    }

    fun loadData(products: ArrayList<ProductView>) {
        val nonNullProducts = products.filter { it != null }
        adapter!!.notifyChange(nonNullProducts)
        swiperefresh.isRefreshing = false
    }

    fun onClickListener(product: ProductView, index: Int) {
        val bundle = Bundle()
        bundle.putParcelable("product", product)
        NavHostFragment.findNavController(main_navigation)
            .navigate(R.id.fragment_product_details, bundle)
    }

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
