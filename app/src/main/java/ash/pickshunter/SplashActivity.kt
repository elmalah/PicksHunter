package ash.pickshunter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.crashlytics.android.Crashlytics
import com.fly365.utils.injection.InjectorUtils
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.fragment_go_trip_details.*

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
            viewModel.getTripDetails(user!!.id, "true").observe(this) {
                if (it.count() > 0)
                    trip = it[0]
            }
        }

        handler.postDelayed({
            if (user == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                if (!user!!.isPhoneVerified)
                    startActivity(Intent(this, SignUpMobileActivity::class.java))
                else {

                    val userType = PreferenceHelper(this).userType

                    //Toast.makeText(this, userType, Toast.LENGTH_LONG).show()

                    if (userType == null || userType == "")
                        startActivity(Intent(this, PicksHunterTypeActivity::class.java))
                    else if (userType == "customer") {
                        userViewModel.checkIfIntrestsSaved(user!!.id.toString()).observe(this) {
                            if (it) {
                                startActivity(Intent(this, MainActivity::class.java))
                            } else {
                                startActivity(Intent(this, GenderInterestActivity::class.java))
                            }

                        }

                    } else if (userType == "hunter") {
                        if (trip != null && trip?.tripId != null) {
                            val intent =
                                Intent(this, MainActivity::class.java).putExtra(
                                    "type",
                                    "tripDetails"
                                )
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        } else {
                            val intent =
                                Intent(this, MainActivity::class.java).putExtra("type", "hunter")
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }

                    }
                }
            }
            finish()
        }, 2000)
    }
}
