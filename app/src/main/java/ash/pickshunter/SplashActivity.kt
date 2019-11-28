package ash.pickshunter

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class SplashActivity : AppCompatActivity() {

    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Fabric.with(this, Crashlytics())
        user = PreferenceHelper(this).user
        val handler = Handler()
        handler.postDelayed( {
            if (user == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            else {
                if (!user!!.isPhoneVerified)
                    startActivity(Intent(this, SignUpMobileActivity::class.java))
                else
                    startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 2000)
    }
}
