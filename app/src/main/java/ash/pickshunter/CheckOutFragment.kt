package ash.pickshunter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import ash.pickshunter.country.Option
import com.fly365.utils.injection.InjectorUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_choose_brand.*
import kotlinx.android.synthetic.main.activity_sign_up_mobile.*
import kotlinx.android.synthetic.main.add_address_popup.view.*
import kotlinx.android.synthetic.main.fragment_check_out.*
import kotlinx.android.synthetic.main.fragment_new_product_step_two.*

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
    lateinit var adapter2: ProductAttributeAdapter
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
                .placeholder(R.drawable.placeholder).into(iv_product)

        tv_product_title.text = product.productName

        tv_price.text = product.displayPrice

        et_product_desc.text = product.description


        adapter2 = ProductAttributeAdapter(arrayListOf(), ::onOptionClickListener)

        tv_attributes.adapter = adapter2
        adapter2.notifyChange(ArrayList(product.productAttributesDetailed))


        addressAdapter = AddressAdapter(arrayListOf(), ::onClickListener)

        var userAddresses = PreferenceHelper(requireContext()).user.addresses
        tv_adddresses.adapter = addressAdapter
        addressAdapter.notifyChange(ArrayList(userAddresses))
        selectedAddressId = userAddresses!!.get(0).id
        ln_add_address.setOnClickListener(this)

        var orderRequest = OrderRequest()
        var order = Order()

        

        orderRequest.order
        bt_place_order.setOnClickListener() {
            ProgressDialog.show(requireContext(), false)
            viewModel.addOrder(orderRequest).observe(this) {
                ProgressDialog.dismiss()

                Toast.makeText(requireContext(), "Order placed successfully", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    fun onClickListener(address: Address, i: Int) {
        var user = PreferenceHelper(requireContext()).user
        selectedAddressId = address.id
        user.addresses!!.map { it.selected = false }
        user.addresses!![i].selected = true
        PreferenceHelper(requireContext()).putUser(user)
        addressAdapter.notifyChange(ArrayList(user.addresses))
    }

    fun onOptionClickListener(option: Option, optionPos: Int, attPos: Int) {
        product.productAttributesDetailed!!.map { it.options?.map { it.selected = false } }
        product.productAttributesDetailed!![attPos].options!![optionPos].selected = true
        adapter2.notifyChange(ArrayList(product.productAttributesDetailed))
    }

    fun addAddressPopupShow() {
        //Inflate the dialog with custom view
        val mDialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.add_address_popup, null)
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
        //cancel button click of custom layout
//        mDialogView.dialogAddBtn.setOnClickListener {
//            //dismiss dialog
//            mAlertDialog.dismiss()
//        }
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
                Toast.makeText(requireContext(), "Address added successfully", Toast.LENGTH_LONG)
                    .show()
            } else {
                //Toast.makeText(requireContext(), "No", Toast.LENGTH_LONG).show()
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



