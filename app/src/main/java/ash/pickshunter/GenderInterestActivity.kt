package ash.pickshunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import ash.pickshunter.databinding.ActivityGenderInterestBinding
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_gender_interest.*

class GenderInterestActivity : AppCompatActivity(), View.OnClickListener {

    var menSelected = false
    var womanSelected = false
    var kidsSelected = false
    lateinit var binding: ActivityGenderInterestBinding
    lateinit var user: User

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_gender_interest
        )
        user = PreferenceHelper(this).user
//        setContentView(R.layout.activity_gender_interest)
        binding.menSelected = menSelected
        binding.womanSelected = womanSelected
        binding.kidsSelected = kidsSelected
        btn_continue.setOnClickListener(this)
        ll_men.setOnClickListener(this)
        ll_woman.setOnClickListener(this)
        ll_kids.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            btn_continue -> {
                updateGenderInterest()
            }
            ll_men -> {
                menSelected = !menSelected
                binding.menSelected = menSelected

            }
            ll_woman -> {
                womanSelected = !womanSelected
                binding.womanSelected = womanSelected
            }
            ll_kids -> {
                kidsSelected = !kidsSelected
                binding.kidsSelected = kidsSelected
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
            Toast.makeText(this, "Please Enter Interests", Toast.LENGTH_LONG).show()
            return
        }
        ids = ids.substring(0, ids.length - 1)
        ProgressDialog.show(this, false)
        viewModel.updateGenderInterest(ids, user.id).observe(this) {
            ProgressDialog.dismiss()
            startActivity(Intent(this, ChooseBrandActivity::class.java))
        }
    }
}
