package ash.pickshunter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_picks_hunter_type.*
import kotlinx.android.synthetic.main.fragment_check_out.*

class PicksHunterTypeActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picks_hunter_type)
        ll_customer.setOnClickListener(this)
        ll_hunter.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0) {
            ll_customer -> {

//                startActivity(Intent(this, GenderInterestActivity::class.java))
//                val intent = Intent(this, MainActivity::class.java).putExtra("type", "Customer")
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                startActivity(intent)
                PreferenceHelper(this).putUserType("customer")
                startActivity(
                    Intent(this, GenderInterestActivity::class.java).putExtra(
                        "type",
                        "Customer"
                    )
                )
            }
            ll_hunter -> {
                PreferenceHelper(this).putUserType("hunter")
                if (    PreferenceHelper(this).tripId == 0){
                    val intent =
                        Intent(this, MainActivity::class.java).putExtra("type", "hunter")
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }

                var user = PreferenceHelper(this).user
                viewModel.getTripDetails(user!!.id, "true").observe(this) {
                    if (it[0] != null && it[0]?.tripId != null) {
                        val intent =
                            Intent(this, MainActivity::class.java).putExtra("type", "tripDetails")
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    } else {
                        val intent =
                            Intent(this, MainActivity::class.java).putExtra("type", "hunter")
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }
                }

                //startActivity(intent)
                //startActivity(Intent(this, MainActivity::class.java).putExtra("type", "hunter"))
            }
        }
    }

}
