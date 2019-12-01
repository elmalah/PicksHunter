package ash.pickshunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.observe
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.fly365.utils.injection.InjectorUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_view_toolbar.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_holder_drawer)
        setupNavigationDrawer()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_nv)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        var user: User? = PreferenceHelper(this).user
        var trip = TripDetailsResponse()

        val userType = PreferenceHelper(this).userType

        if (userType == "customer") {
            NavHostFragment.findNavController(main_navigation)
                .setGraph(R.navigation.customer_navigation)

        } else if (userType == "hunter") {
            if (user != null) {
                viewModel.getTripDetails(user!!.id, "true").observe(this) {
                    NavHostFragment.findNavController(main_navigation)
                        .setGraph(R.navigation.hunter_navigation)

                    if (it.count() > 0) {
                        trip = it[0]

                        if (trip != null && trip?.tripId != null) {
                            var navOptions =
                                NavOptions.Builder().setPopUpTo(R.id.fragment_plan_trip, true)
                                    .build()
                            NavHostFragment
                                .findNavController(main_navigation)
                                .navigate(
                                    R.id.action_navigate_to_go_to_trip_details,
                                    null,
                                    navOptions
                                )
                        }
                    }
                }
            }
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

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
        false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_switch -> {
                val intent = Intent(this, PicksHunterTypeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }

        when (item.itemId) {
            R.id.nav_logout -> {
                PreferenceHelper(this).putUser(null)

                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
