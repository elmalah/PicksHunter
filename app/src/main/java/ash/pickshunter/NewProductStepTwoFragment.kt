package ash.pickshunter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.fly365.utils.injection.InjectorUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_new_product_step_one.*
import android.widget.AdapterView
import androidx.lifecycle.map
import androidx.navigation.fragment.NavHostFragment
import ash.pickshunter.country.Option
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_new_product_step_one.bt_add_product
import kotlinx.android.synthetic.main.fragment_new_product_step_two.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NewProductStepOneFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NewProductStepOneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewProductStepTwoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var shop: Shop? = null
    private var product: Product? = null
    lateinit var adapter1: AttributeAdapter
    lateinit var adapter2: ProductAttributeAdapter

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        shop = arguments?.getParcelable("shop")
        product = arguments?.getParcelable("product")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_product_step_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter1 = AttributeAdapter(arrayListOf(), ::onOptionSelectedListener)
        adapter2 = ProductAttributeAdapter(arrayListOf(), ::onOptionClickListener)


        ProgressDialog.show(requireContext(), false)
        viewModel.getAttributes(product?.categoryIds!![0]).observe(this) {
            ProgressDialog.dismiss()
            rv_attributes.adapter = adapter1
            adapter1.notifyChange(ArrayList(it.specificationAttributes))

            rv_attributes_product.adapter = adapter2
            adapter2.notifyChange(ArrayList(it.productAttributes))
        }

        bt_add_product.setOnClickListener {
            ProgressDialog.show(requireContext(), false)
            val productRequest = ProductRequest()
            viewModel.attributeApiResponse.value!!.productAttributes!!.map { att->
                val option = att.options?.filter { it.selected }
                var attribute_values: ArrayList<AttributeValues> = arrayListOf()
                option?.map {
                    attribute_values.add(AttributeValues(it.name)) }
                product!!.attributes.add(AttributeRequest(att.id, attribute_values))  }
            productRequest.product = product!!
            viewModel.updateProduct(productRequest, product!!.id!!).observe(this) {
                ProgressDialog.dismiss()
                val bundle = Bundle()
                bundle.putParcelable("product", product)
                NavHostFragment.findNavController(navigation_trip).navigate(R.id.fragment_product_pricing, bundle)
            }
        }
        getBrands()
    }

    fun onOptionSelectedListener(option: Option, optionPos: Int, attPos: Int) {
        if (product!!.productSpecificationAttributes.size >= attPos)
            product!!.productSpecificationAttributes.add(attPos, ProductSpecificationAttributes(option.id!!))
        else
            product!!.productSpecificationAttributes[attPos] = ProductSpecificationAttributes(option.id!!)
    }

    fun onOptionClickListener(option: Option, optionPos: Int, attPos: Int) {
        viewModel.onOptionChange(option, optionPos, attPos)
    }

    fun getBrands() {
//        ProgressDialog.show(requireContext(), false)
        viewModel.getCategories().observe(this) {
//            ProgressDialog.dismiss()
//            val options = it.categories
//            val adapter : ArrayAdapter<Category> =  ArrayAdapter<Category>(requireContext(), android.R.layout.simple_spinner_item, options)
//            spinner_category.adapter = adapter
//            spinner_category.setSelection(0)
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
         * @return A new instance of fragment NewProductStepOneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewProductStepOneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}