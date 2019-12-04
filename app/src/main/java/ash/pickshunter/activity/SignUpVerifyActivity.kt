package ash.pickshunter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import ash.pickshunter.*
import ash.pickshunter.model.User
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.UserViewModel
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_sign_up_verify.*

class SignUpVerifyActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var user: User

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_verify)
        user = PreferenceHelper(this).user
        btn_get_started.setOnClickListener(this)
        tv_resend.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btn_get_started -> {
                verifyPhone()
            }
            tv_resend -> {
                ProgressDialog.show(this, false)
                viewModel.resendVerificationCode(user.id).observe(this) {
                    ProgressDialog.dismiss()
                    Toast.makeText(this, "Please Check Your Mobile", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun verifyPhone() {
        if (et_code_1.text.length < 6) {
            Toast.makeText(this, "Please Enter Valid Code", Toast.LENGTH_LONG).show()
            return
        }
        ProgressDialog.show(this, false)
        viewModel.verifyPhone(et_code_1.text.toString(), user.id).observe(this) {
            ProgressDialog.dismiss()

            it.user = it.users!!.get(0)

            PreferenceHelper(this).putUser(it.user)

            if (it.users != null && it.users!!.isNotEmpty() && !it.user!!.isPhoneVerified) {
                Toast.makeText(this, "Invalid verification code", Toast.LENGTH_LONG).show()
            }
            else {
                val intent = Intent(this, PicksHunterTypeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finishAffinity()
            }
        }
    }
}
