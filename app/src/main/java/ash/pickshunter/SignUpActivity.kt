package ash.pickshunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        tv_login.setOnClickListener(this)
        btn_sign_up.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            tv_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            btn_sign_up -> {
                validate()
            }
        }
    }

    private fun validate() {
        if (et_first_name.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.please_enter_valid_name), Toast.LENGTH_LONG).show()
            return
        }

        if (et_last_name.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.please_enter_valid_name), Toast.LENGTH_LONG).show()
            return
        }

        if (!et_email.text.toString().isEmailValid()) {
            Toast.makeText(this, getString(R.string.please_enter_valid_email), Toast.LENGTH_LONG).show()
            return
        }

        if (et_password.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.please_enter_password), Toast.LENGTH_LONG).show()
            return
        }

        val registrationRequest = RegistrationRequest()
        registrationRequest.customer.email = et_email.text.toString()
        registrationRequest.customer.password = et_password.text.toString()
        registrationRequest.customer.firstName = et_first_name.text.toString()
        registrationRequest.customer.lastName = et_last_name.text.toString()

        ProgressDialog.show(this, false)
        viewModel.register(registrationRequest).observe(this) {
            ProgressDialog.dismiss()
            if (it.users != null && it.users!!.isNotEmpty()) {
                startActivity(Intent(this, SignUpMobileActivity::class.java).putExtra("user", it.users!![0]))
            }
            else {
                Toast.makeText(this, it.errors!!.Email!![0], Toast.LENGTH_LONG).show()
            }
        }
    }
}
