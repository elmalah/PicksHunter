package ash.pickshunter.fragment

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import ash.pickshunter.*
import ash.pickshunter.adapter.AttributeViewAdapter
import ash.pickshunter.adapter.CommentAdapter
import ash.pickshunter.adapter.SliderAdapter
import ash.pickshunter.model.ProductCommentRequest
import ash.pickshunter.model.ProductView
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.TripViewModel
import ash.pickshunter.utils.InjectorUtils
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.android.synthetic.main.fragment_product_details.imageSlider

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

    lateinit var product: ProductView

    var sliderAdapter: SliderAdapter? = null


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

        sliderAdapter = SliderAdapter(arrayListOf(), false, this::onClickListener)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (PreferenceHelper(requireContext()).userType == "hunter")
            fb_checkout.hide()

        imageSlider.setSliderAdapter(sliderAdapter)
        imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT)
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.isAutoCycle = false

        imageSlider.setCurrentPageListener { }
        sliderAdapter!!.notifyChange(product.productImages!!)

        tv_location.text = product.date
        tv_name.text = product.shopperName

//        if (product.productImages != null && product.productImages!!.isNotEmpty())
//            Picasso.get().load(product.productImages!!.getOrNull(0))
//                .placeholder(R.drawable.placeholder).into(iv_product_image)

        Picasso.get().load(product.tripCountryFlag)
            .placeholder(R.drawable.placeholder).into(iv_country_flag)

        Picasso.get()
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSLbcOjGm-n-4Mtwbf2wE4t-1fu5puULtQ6YpfcJD5ZEuIspLK_")
            .placeholder(R.drawable.placeholder).into(iv_user)

        tv_product_title.text = product.productName

        //tv_from.text = product.fromDate + " " + product.fromCountryAndState
        //tv_to.text = product.toDate + " " + product.toCountryAndState

        tv_returnsIn.text = product.returnsIn

        tv_brand_name.text = product.shopName

        tv_desc.text = product.description

        tv_category.text = product.category

        tv_price.text = product.displayPrice

        tv_comments_count.text = product.comments!!.count().toString()

        Picasso.get().load(product.shopperAvatar)
            .placeholder(R.drawable.placeholder).into(iv_avatar)

        var adapter = CommentAdapter(arrayListOf())
        adapter.notifyChange(product.comments!!)

        rv_comments.adapter = adapter

        var adapter2 = AttributeViewAdapter(arrayListOf())
        adapter2.notifyChange(product.productAttributes!! + product.specificationAttributes!!)

        rv_specification_attributes.adapter = adapter2

        tv_post.setOnClickListener {
            if (et_comment.text.isNotEmpty()) {
                val productCommentRequest = ProductCommentRequest()
                productCommentRequest.productComment.comment = et_comment.text.toString()
                productCommentRequest.productComment.customerId =
                    PreferenceHelper(requireContext()).user.id
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

        iv_avatar.setOnClickListener(clickListener)

        tv_name.setOnClickListener(clickListener)

        fb_checkout.setOnClickListener() {
            val bundle = Bundle()
            bundle.putParcelable("product", product)
            NavHostFragment.findNavController(main_navigation)
                .navigate(R.id.fragment_check_out, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    val clickListener = View.OnClickListener { view ->
        var userType = PreferenceHelper(requireContext()).userType
        if (userType == "customer") {
            val bundle = Bundle()
            bundle.putInt("profileCustomerId", product.shopperCustomerId!!)
            NavHostFragment.findNavController(main_navigation)
                .navigate(R.id.fragment_customer_profile, bundle)
        } else if (userType == "hunter" && PreferenceHelper(requireContext()).user.id == product.shopperCustomerId) {
            //navigate to my profile
        }

    }

    private fun onClickListener(image: String, position: Int) {

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
