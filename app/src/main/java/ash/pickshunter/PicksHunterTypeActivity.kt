package ash.pickshunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_picks_hunter_type.*

class PicksHunterTypeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picks_hunter_type)
        ll_customer.setOnClickListener(this)
        ll_hunter.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when  (p0) {
            ll_customer -> {
//                startActivity(Intent(this, GenderInterestActivity::class.java))
//                val intent = Intent(this, MainActivity::class.java).putExtra("type", "Customer")
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                startActivity(intent)
                startActivity(Intent(this, GenderInterestActivity::class.java).putExtra("type", "Customer"))
            }
            ll_hunter -> {
                val intent = Intent(this, MainActivity::class.java).putExtra("type", "hunter")
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
//                startActivity(Intent(this, MainActivity::class.java).putExtra("type", "hunter"))
            }
        }
    }
}
