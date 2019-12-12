package ash.pickshunter.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
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
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.fragment.NavHostFragment
import ash.pickshunter.*
import ash.pickshunter.model.*
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.TripViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


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
class NewProductStepOneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var shop: Shop? = null
    private var categoryId: Int = 0
    private var pictureId: Int? = null

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
        //shop = Shop()
        //shop!!.id = 85
        //shop!!.tripShopId = 85
        //shop!!.name = "dds"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_product_step_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_name.text = shop!!.name

        Picasso.get().load(shop!!.logo)
            .placeholder(R.drawable.placeholder).into(iv_store)

        iv_product.setOnClickListener {

            NavHostFragment.findNavController(main_navigation)
                .navigate(R.id.fragment_product_pictures)
        }

        bt_add_product.setOnClickListener {

            if (tv_product_title.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter Product Title", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            if (et_product_desc.text.toString().isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please Enter Product Description",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            var productPicture = ProductPicture()
            productPicture.pictureId = pictureId

            ProgressDialog.show(requireContext(), false)
            val productRequest = ProductRequest()
            productRequest.product.name = tv_product_title.text.toString()
            productRequest.product.shortDescription = et_product_desc.text.toString()
            productRequest.product.categoryIds?.add(0, categoryId)
            productRequest.product.images?.add(productPicture)
            viewModel.addProduct(productRequest, shop!!.tripShopId!!).observe(this) {
                ProgressDialog.dismiss()
                val bundle = Bundle()
                bundle.putParcelable("product", it.products!![0])
                NavHostFragment.findNavController(main_navigation)
                    .navigate(R.id.fragment_new_product_step_two, bundle)
            }
        }
        getCategories()
    }

    fun getCategories() {
        ProgressDialog.show(requireContext(), false)
        viewModel.getCategories().observe(this) {
            ProgressDialog.dismiss()
            val options = it.categories
            val adapter: ArrayAdapter<Category> = ArrayAdapter<Category>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                options
            )
            spinner_category.adapter = adapter
            spinner_category.setSelection(0)
        }
        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                categoryId = viewModel.getCategoriesList()[position].id
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
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
