package ash.pickshunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
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
            user.isPhoneVerified= true
            PreferenceHelper(this).putUser(user)
            startActivity(Intent(this, PicksHunterTypeActivity::class.java))
        }
    }
}