package ash.pickshunter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import com.fly365.utils.injection.InjectorUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product_details.*

// TODO: Rename parameter arguments, choose names that match
// the fragment inproductialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activproducties that contain this fragment must implement the
 * [ProductDetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProductDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var product: Product

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        product = arguments!!.getParcelable("product")


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        date.text = product.date
        tv_name.text = product.shopperName
        if (product.productImages != null && product.productImages!!.isNotEmpty())
            Picasso.get().load(product.productImages!![0])
                .placeholder(R.drawable.loginlogo).into(iv_product_image)

        Picasso.get().load(product.tripCountryFlag)
            .placeholder(R.drawable.loginlogo).into(iv_country_flag)

        tv_product_title.text = product.productName

        tv_from.text = product.fromCountryAndState + " " + product.fromDate

        tv_to.text = product.toCountryAndState + " " + product.toDate

        tv_brand_name.text = product.shopName

        tv_desc.text = product.description

        tv_price.text = product.price

        Picasso.get().load(product.shoperAvatar)
            .placeholder(R.drawable.loginlogo).into( iv_product)

        var adapter = CommentAdapter(arrayListOf())
        adapter.notifyChange(product.comments!!)

        rv_comments.adapter = adapter

        var adapter2 = AttributeViewAdapter(arrayListOf())
        adapter2.notifyChange(product.productAttributes!! + product.specificationAttributes!!)

        rv_attributes.adapter = adapter2

        tv_post.setOnClickListener {
            if(et_comment.text.isNotEmpty()) {
                val productCommentRequest = ProductCommentRequest()
                productCommentRequest.productComment.comment = et_comment.text.toString()
                productCommentRequest.productComment.customerId = PreferenceHelper(requireContext()).user.id
                productCommentRequest.productComment.productId = product.productId
                ProgressDialog.show(requireContext(), false)
                viewModel.addComment(productCommentRequest).observe(this) {
                    ProgressDialog.dismiss()
                    product = it
                    adapter.notifyChange(it.comments!!)
                    et_comment.setText("")
                }
            }
        }

        fb_checkout.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("product", product)
            NavHostFragment.findNavController(navigation_trip).navigate(R.id.fragment_check_out, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    /**
     * This interface must be implemented by activproducties that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activproducty and potentially other fragments contained in that
     * activproducty.
     *
     *
     * See the Android Training lesson [Communicating wproducth Other Fragments]
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
         * @return A new instance of fragment ProductDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
