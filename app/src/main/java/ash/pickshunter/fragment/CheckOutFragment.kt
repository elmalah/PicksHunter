package ash.pickshunter.fragment

import android.app.AlertDialog
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
import ash.pickshunter.*
import ash.pickshunter.adapter.AddressAdapter
import ash.pickshunter.adapter.CheckoutProductAttributeAdapter
import ash.pickshunter.model.*
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.TripViewModel
import ash.pickshunter.viewModel.UserViewModel
import ash.pickshunter.utils.InjectorUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup_add_address.view.*
import kotlinx.android.synthetic.main.fragment_check_out.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var selectedAddressId: Int? = null

class CheckOutFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when (p0) {
            ln_add_address -> {
                //Toast.makeText(requireContext(), "Ad Address", Toast.LENGTH_LONG).show()
                addAddressPopupShow()
            }
        }
    }


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var product: ProductView
    lateinit var checkoutProductAttributeAdapter: CheckoutProductAttributeAdapter
    lateinit var addressAdapter: AddressAdapter

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }
    private val userViewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        product = arguments!!.getParcelable("product")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (product.productImages != null && !product.productImages!!.isEmpty())
            Picasso.get().load(product.productImages!![0])
                .placeholder(R.drawable.placeholder).into(iv_avatar)

        tv_product_title.text = product.productName
        tv_price.text = product.displayPrice
        tv_delivery_time.text = product.toDate
        et_product_desc.text = product.description


        checkoutProductAttributeAdapter = CheckoutProductAttributeAdapter(arrayListOf(), ::onOptionClickListener)

        tv_attributes.adapter = checkoutProductAttributeAdapter
        checkoutProductAttributeAdapter.notifyChange(ArrayList(product.productAttributesDetailed))


        addressAdapter = AddressAdapter(arrayListOf(), ::onClickListener)

        var userAddresses = PreferenceHelper(requireContext()).user.addresses
        tv_adddresses.adapter = addressAdapter
        addressAdapter.notifyChange(ArrayList(userAddresses))
        if (userAddresses!!.count() > 0)
            selectedAddressId = userAddresses!!.get(0)?.id
        ln_add_address.setOnClickListener(this)



        bt_place_order.setOnClickListener() {
            var valid = true

            if (selectedAddressId == null || selectedAddressId == 0) {
                Toast.makeText(requireContext(), "Please select or add address", Toast.LENGTH_LONG)
                    .show()
                valid = false
            }

            for (att in product.productAttributesDetailed!!) {
                if (att.options?.any() {
                        it.selected
                    } == false) {

                    Toast.makeText(
                        requireContext(),
                        "Please select " + att.name,
                        Toast.LENGTH_LONG
                    ).show()

                    valid = false
                }
            }

            if (valid) {
                var orderRequest = prepareOrderRequest()

                ProgressDialog.show(requireContext(), false)
                viewModel.addOrder(orderRequest).observe(this) {
                    ProgressDialog.dismiss()

                    Toast.makeText(requireContext(), "Order placed successfully", Toast.LENGTH_LONG)
                        .show()

                    val bundle = Bundle()
                    bundle.putParcelable("order", it.orders!![0])
                    NavHostFragment.findNavController(main_navigation)
                        .navigate(R.id.fragment_time_line, bundle)
                }
            }
        }
    }

    fun prepareOrderRequest(): OrderRequest {
        var orderRequest = OrderRequest()
        var order = Order()
        order.shippingMethod = "Ground"
        order.shippingRateComputationMethodSystemName = "Shipping.FixedByWeightByTotal"
        order.paymentMethodSystemName = "Payments.CashOnDelivery"
        order.customerId = PreferenceHelper(requireContext()).user.id
        order.orderTotal = product.price
        order.billingAddressId = selectedAddressId
        order.shippingAddressId = selectedAddressId

        var orderItems = ArrayList<OrderItem>()
        var orderItem = OrderItem()
        orderItem.quantity = 1
        orderItem.productId = product.productId

        var productAttributes = ArrayList<ProductAttributeItem>()

        for (att in product.productAttributesDetailed!!) {
            var productAttributeItem = ProductAttributeItem()

            productAttributeItem.id = att.productAttributeMappingId
            productAttributeItem.value = att.options?.firstOrNull() {
                it.selected == true
            }?.key

            productAttributes.add(productAttributeItem)

        }

        orderItem.productAttributes = productAttributes

        orderItems.add(orderItem)
        order.orderItems = orderItems
        orderRequest.order = order

        return orderRequest
    }

    fun onClickListener(address: Address, i: Int) {
        var user = PreferenceHelper(requireContext()).user
        selectedAddressId = address.id
        user.addresses!!.map { it.selected = false }
        user.addresses!![i].selected = true
        PreferenceHelper(requireContext()).putUser(user)
        addressAdapter.notifyChange(ArrayList(user.addresses))
    }

    fun onOptionClickListener(option: ProductAttributeDetailedOption, optionPos: Int, attPos: Int) {
        product.productAttributesDetailed!!.map { it.options?.map { it.selected = false } }
        product.productAttributesDetailed!![attPos].options!![optionPos].selected = true
        checkoutProductAttributeAdapter.notifyChange(ArrayList(product.productAttributesDetailed))
    }

    fun addAddressPopupShow() {
        //Inflate the dialog with custom view
        val mDialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.popup_add_address, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)
            .setTitle("Add Address")
        //show dialog
        val mAlertDialog = mBuilder.show()
        //login button click of custom layout
        mDialogView.dialogAddBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout
            val name = mDialogView.dialogNameEt.text.toString()
            val city = mDialogView.dialogEmailEt.text.toString()
            //set the input text in TextView
//            mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
            postNewAddress(name, city)
        }
    }

    private fun postNewAddress(name: String, city: String) {
        val registrationRequest = RegistrationRequest()
        registrationRequest.customer.id = PreferenceHelper(requireContext()).user.id
        registrationRequest.customer.addresses.add(Address(name, city))
        ProgressDialog.show(requireContext(), false)
        userViewModel.updateUser(registrationRequest).observe(this) {
            ProgressDialog.dismiss()
            Toast.makeText(requireContext(), "text address added ", Toast.LENGTH_LONG).show()
            if (it.users != null && it.users!!.isNotEmpty()) {
                PreferenceHelper(requireContext()).putUser(it.users!![0])
                var addresses = PreferenceHelper(requireContext()).user.addresses
                addressAdapter.notifyChange(ArrayList(addresses))
                var lastIndex = addresses.count() - 1
                onClickListener(addresses[lastIndex], lastIndex)
                selectedAddressId = addresses[lastIndex].id
                Toast.makeText(requireContext(), "Address added successfully", Toast.LENGTH_LONG)
                    .show()
            }
        }
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
         * @return A new instance of fragment CheckOutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CheckOutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



