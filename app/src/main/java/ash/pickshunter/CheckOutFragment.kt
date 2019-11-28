package ash.pickshunter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ash.pickshunter.country.Option
import com.fly365.utils.injection.InjectorUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_check_out.*
import kotlinx.android.synthetic.main.fragment_new_product_step_two.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CheckOutFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CheckOutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckOutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var product: Product
    lateinit var adapter2 : ProductAttributeAdapter

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(product.productImages != null && !product.productImages!!.isEmpty())
        Picasso.get().load(product.productImages!![0])
            .placeholder(R.drawable.loginlogo).into(iv_product)

        tv_product_title.text = product.productName

        tv_price.text = product.price

        et_product_desc.text = product.description

        adapter2 = ProductAttributeAdapter(arrayListOf(), ::onOptionClickListener)

        tv_attributes.adapter = adapter2
        adapter2.notifyChange(ArrayList(product.ProductAttributesDetailed))
    }

    fun onOptionClickListener(option: Option, optionPos: Int, attPos: Int) {
       product.ProductAttributesDetailed!!.map { it.options?.map { it.selected = false } }
        product.ProductAttributesDetailed!![attPos].options!![optionPos].selected = true
        adapter2.notifyChange(ArrayList(product.ProductAttributesDetailed))
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
