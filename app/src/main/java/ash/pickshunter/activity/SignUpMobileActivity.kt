package ash.pickshunter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import ash.pickshunter.*
import ash.pickshunter.model.RegistrationRequest
import ash.pickshunter.model.User
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.UserViewModel
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_sign_up_mobile.*

class SignUpMobileActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var user: User

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_mobile)
        user = PreferenceHelper(this).user
        et_mobile_number.setText(user.phone)
        bt_sign_up.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            bt_sign_up -> {
                validate()
            }
        }
    }

    private fun validate() {
        if (et_mobile_number.text.toString().length < 10) {
            Toast.makeText(this, getString(R.string.please_enter_valid_mobile_number), Toast.LENGTH_LONG).show()
            return
        }

        val registrationRequest = RegistrationRequest()
        registrationRequest.customer.phone = et_mobile_number.text.toString()
        registrationRequest.customer.id = user.id
        ProgressDialog.show(this, false)
        viewModel.updateUser(registrationRequest).observe(this) {
            ProgressDialog.dismiss()
            if (it.users != null && it.users!!.isNotEmpty()) {
                startActivity(Intent(this, SignUpVerifyActivity::class.java).putExtra("user", user))          }
            else {
                Toast.makeText(this, it.errors!!.Email!![0], Toast.LENGTH_LONG).show()
            }
        }
    }
}
