package ash.pickshunter

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product_pricing.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProductPricingFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProductPricingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductPricingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var type = 1
    var price: Double? = null
    var priceEgp: Double? = null
    var cost: Double? = null
    var costEgp: Double? = null

    private var product: Product? = null

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        product = arguments?.getParcelable("product")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_pricing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ll_comission.setOnClickListener {
            type = 1
            iv_select_commission.visibility = View.VISIBLE
            ll_comission.backgroundTintList =
                resources.getColorStateList(R.color.colorOrange)

            iv_select_amount.visibility = View.INVISIBLE
            ll_amount.backgroundTintList =
                resources.getColorStateList(R.color.colorGray2)
        }

        ll_amount.setOnClickListener {
            type = 2
            iv_select_commission.visibility = View.INVISIBLE
            ll_comission.backgroundTintList =
                resources.getColorStateList(R.color.colorGray2)

            iv_select_amount.visibility = View.VISIBLE
            ll_amount.backgroundTintList =
                resources.getColorStateList(R.color.colorOrange)
        }

        ProgressDialog.show(requireContext(), false)
        viewModel.getProduct(product?.id!!.toInt()).observe(this) {
            ProgressDialog.dismiss()
            product = it!!.products!!.get(0)
            product?.attributes = ArrayList<AttributeRequest>()
            product?.productSpecificationAttributes = ArrayList<ProductSpecificationAttributes>()
        }

        btn_calculate.setOnClickListener {
            if (et_store_price.text.isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter Store Price", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            ProgressDialog.show(requireContext(), false)
            val priceRequest = PriceRequest()
            priceRequest.price.earningApproach = type
            priceRequest.price.storePrice = et_store_price.text.toString().toDouble()
            if (et_vat.text.toString().isNotEmpty())
                priceRequest.price.vatPercentage = et_vat.text.toString().toDouble()
            if (et_value.text.toString().isNotEmpty())
                priceRequest.price.shopperComission = et_value.text.toString().toDouble()
            if (et_sale.text.toString().isNotEmpty())
                priceRequest.price.storeSale = et_sale.text.toString().toDouble()
            viewModel.calculatePrice(priceRequest).observe(this) {
                ProgressDialog.dismiss()
                price = it.totalPrice
                priceEgp = it.totalPriceEgp

                cost = it.cost
                costEgp = it.costEgp

                tv_vat.text = it.vatAmount.toString() + " $"
                tv_app_comission.text = it.appComission.toString() + " %"
                tv_total_price.text =
                    ((Math.round(it.totalPrice!! * 100)) / 100.0).toString() + " $"

                tv_total_price_egp.text =
                    ((Math.round(it.totalPriceEgp!! * 100)) / 100.0).toString() + " EGP"
            }
        }

        bt_add_product.setOnClickListener {
            if (price == null) {
                Toast.makeText(requireContext(), "Please Calculate Price", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            ProgressDialog.show(requireContext(), false)

            val productRequest = ProductRequest()
            //var productId = product!!.id
            //product = Product()
            //product!!.id = productId

            product!!.price = priceEgp
            product!!.productCost = costEgp
            product!!.published = true

            productRequest.product = product!!

            viewModel.updateProduct(productRequest, product!!.id!!).observe(this) {
                ProgressDialog.dismiss()

                Toast.makeText(requireContext(), "Product Updated Successfully", Toast.LENGTH_LONG)
                    .show()

                NavHostFragment.findNavController(main_navigation)
                    .navigate(R.id.fragment_go_to_trip_details)
            }
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
         * @return A new instance of fragment ProductPricingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductPricingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
