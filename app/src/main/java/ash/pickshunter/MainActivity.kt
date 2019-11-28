package ash.pickshunter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            if (intent.getStringExtra("type") == "hunter")
            {
                NavHostFragment.findNavController(navigation_trip).navigate(R.id.fragment_plan_trip)
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
