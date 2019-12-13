package ash.pickshunter.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import ash.pickshunter.*
import ash.pickshunter.model.TripDetailsResponse
import ash.pickshunter.model.User
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.viewModel.TripViewModel
import ash.pickshunter.viewModel.UserViewModel
import com.crashlytics.android.Crashlytics
import ash.pickshunter.utils.InjectorUtils
import io.fabric.sdk.android.Fabric

class SplashActivity : AppCompatActivity() {

    var user: User? = null

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(this)
    }
    private val userViewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Fabric.with(this, Crashlytics())
        user = PreferenceHelper(this).user
        val handler = Handler()
        var trip = TripDetailsResponse()

        if (user != null) {
            viewModel.getTripDetails(
                user!!.id,
                false,
                false,
                false,
                false
            ).observe(this) {
                if (it.count() > 0)
                    trip = it[0]
            }
        }

        handler.postDelayed({
            if (user == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                if (!user!!.isPhoneVerified) {
                    startActivity(Intent(this, SignUpMobileActivity::class.java))
                    finish()
                } else {

                    val userType = PreferenceHelper(this).userType

                    if (userType == null || userType == "") {
                        startActivity(Intent(this, PicksHunterTypeActivity::class.java))
                        finish()
                    } else if (userType == "customer") {
                        userViewModel.checkIfIntrestsSaved(user!!.id.toString()).observe(this) {
                            if (it) {
                                startActivity(Intent(this, MainActivity::class.java))
                            } else {
                                startActivity(Intent(this, GenderInterestActivity::class.java))
                            }
                            finish()
                        }
                    } else if (userType == "hunter") {

                        startActivity(Intent(this, MainActivity::class.java))

                        //if (trip != null && trip?.tripId != null) {
                        //    val intent =
                        //        Intent(this, MainActivity::class.java).putExtra(
                        //            "type",
                        //            "tripDetails"
                        //        )
                        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        //    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        //    startActivity(intent)
                        //} else {
                        //    val intent =
                        //        Intent(this, MainActivity::class.java).putExtra("type", "hunter")
                        //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        //    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        //    startActivity(intent)
                        //}
                        finish()

                    }
                }
            }
        }, 2000)
    }
}
