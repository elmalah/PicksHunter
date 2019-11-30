package ash.pickshunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_view_toolbar.*
import android.app.Activity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.observe
import java.lang.System.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_holder_drawer)
        setupNavigationDrawer()

        try {
            if (intent.getStringExtra("type") == "hunter") {
                //check if Hunter has Already Active Trip
                NavHostFragment.findNavController(navigation_trip).navigate(R.id.fragment_plan_trip)
            } else if (intent.getStringExtra("type") == "Customer") {
                NavHostFragment.findNavController(navigation_trip)
                    .navigate(R.id.fragment_gender_interests)
            } else if (intent.getStringExtra("type") == "tripDetails") {
                NavHostFragment.findNavController(navigation_trip)
                    .navigate(R.id.fragment_go_to_trip_details)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setupNavigationDrawer() {

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.isDrawerIndicatorEnabled = false
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.setNavigationOnClickListener {
            val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        navView.setNavigationItemSelectedListener(this)
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        var type = this.intent?.getStringExtra("type")

        if (type == "hunter" || type == "tripDetails") {
            if (doubleBackToExitPressedOnce) {
                finish()
            }

            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_switch -> {

                // Handle the Switch Mode action
                val intent = Intent(this, PicksHunterTypeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                //startActivity(Intent(this, PicksHunterTypeActivity::class.java))
            }
        }

        when (item.itemId) {
            R.id.action_home -> {
                var trip = TripDetailsResponse()
                var user = User()

                if (user == null) {


                } else {
                    if (!user!!.isPhoneVerified)
                        startActivity(Intent(this, SignUpMobileActivity::class.java))
                    else {

                        //  val userType = PreferenceHelper(this).userType

                        //  //Toast.makeText(this, userType, Toast.LENGTH_LONG).show()

                        //  if (userType == null || userType == "")
                        //      startActivity(Intent(this, PicksHunterTypeActivity::class.java))
                        //  else if (userType == "customer")
                        //      startActivity(Intent(this, MainActivity::class.java))
                        //  else if (userType == "hunter") {
                        //      if (trip != null && trip?.tripId != null) {
                        //          val intent =
                        //              Intent(this, MainActivity::class.java).putExtra(
                        //                  "type",
                        //                  "tripDetails"
                        //              )
                        //          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        //          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        //          startActivity(intent)
                        //      } else {
                        //          val intent =
                        //              Intent(this, MainActivity::class.java).putExtra(
                        //                  "type",
                        //                  "hunter"
                        //              )
                        //          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        //          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        //          startActivity(intent)
                        //      }

                        //  }
                    }
                }
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
