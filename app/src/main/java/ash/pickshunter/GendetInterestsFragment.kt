package ash.pickshunter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_gender_interest.*


class GenderInterestFragment : Fragment(), View.OnClickListener  {
    var menSelected = false
    var womanSelected = false
    var kidsSelected = false
    //    lateinit var binding: GenderInterestFragmentBinding
    lateinit var user: User

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gender_interests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding = DataBindingUtil.setContentView(
//            this,
//            R.layout.activity_gender_interest
//        )
        user = PreferenceHelper(activity).user
//        setContentView(R.layout.activity_gender_interest)
        menSelected = menSelected
        womanSelected = womanSelected
        kidsSelected = kidsSelected
        btn_continue.setOnClickListener(this)
        ll_men.setOnClickListener(this)
        ll_woman.setOnClickListener(this)
        ll_kids.setOnClickListener(this)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *colo
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlanTripFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlanTripFragment().apply {

            }
    }

    override fun onClick(p0: View?) {
        when(p0) {
            btn_continue -> {
                updateGenderInterest()
            }
            ll_men -> {
                menSelected = !menSelected
                menSelected = menSelected

            }
            ll_woman -> {
                womanSelected = !womanSelected
                womanSelected = womanSelected
            }
            ll_kids -> {
                kidsSelected = !kidsSelected
                kidsSelected = kidsSelected
            }
        }
    }

    private fun updateGenderInterest() {
        var ids = ""
        if (menSelected) {
            ids += "1,"
        }
        if (womanSelected) {
            ids += "2,"
        }
        if (kidsSelected) {
            ids += "3,"
        }
        if (ids == "") {
            Toast.makeText(activity, "Please Enter Interests", Toast.LENGTH_LONG).show()
            return
        }
        ids = ids.substring(0, ids.length - 1)
        ProgressDialog.show(activity, false)
        viewModel.updateGenderInterest(ids, user.id).observe(this) {
            ProgressDialog.dismiss()
            startActivity(Intent(activity, ChooseBrandActivity::class.java))
        }
    }
}
