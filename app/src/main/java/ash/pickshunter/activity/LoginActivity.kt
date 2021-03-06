package ash.pickshunter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.view.View
import android.widget.Toast
import androidx.lifecycle.observe
import ash.pickshunter.*
import ash.pickshunter.model.LoginRequest
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.utils.isEmailValid
import ash.pickshunter.viewModel.UserViewModel
import ash.pickshunter.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tv_sign_up.setOnClickListener(this)
        bt_login.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            tv_sign_up -> {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
            bt_login -> {
                validateLogin()
            }
        }
    }

    private fun validateLogin() {
        if (!et_email.text.toString().isEmailValid()) {
            Toast.makeText(this, getString(R.string.please_enter_valid_email), Toast.LENGTH_LONG)
                .show()
            return
        } else if (et_password.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.please_enter_password), Toast.LENGTH_LONG)
                .show()
            return
        }
        val loginRequest = LoginRequest()
        loginRequest.Email = et_email.text.toString()
        loginRequest.Password = et_password.text.toString()
        ProgressDialog.show(this, false)
        viewModel.login(loginRequest).observe(this) {
            ProgressDialog.dismiss()
            if (it.isSucceeded) {
                PreferenceHelper(this).putUser(it.user)
                if (!it.user!!.isPhoneVerified) {
                    val intent = Intent(this, SignUpMobileActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("user", it.user)
                    startActivity(intent)
                    finishAffinity()
                } else {
                    val intent = Intent(this, PicksHunterTypeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("user", it.user)
                    startActivity(intent)
                    finishAffinity()
                }
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
