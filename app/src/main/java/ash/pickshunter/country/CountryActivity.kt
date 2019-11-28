package ash.pickshunter.country

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import ash.pickshunter.R
import ash.pickshunter.State
import ash.pickshunter.TripViewModel
import ash.pickshunter.UserViewModel
import ash.pickshunter.databinding.ActivityCountryBinding
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_country.*

class CountryActivity : AppCompatActivity() {

    private var adapter: CountryAdapter? = null
    private lateinit var binding: ActivityCountryBinding

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_country
        )
        adapter =
            CountryAdapter(
                arrayListOf(),
                this::onClickListener
            )
        rv_countries.adapter = adapter
        binding.progressBar.visibility = View.VISIBLE
        binding.svCountry.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
//                    val countries = viewModel.
//                    adapter!!.notifyChange(countries)
                    return true
                }
                binding.progressBar.visibility = View.VISIBLE
                Handler().postDelayed({
                    search(newText)
                }, 1000)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return false
            }

        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        subscribeUi()
    }

    private fun search(text: String) {
        val states : ArrayList<State> = arrayListOf()
        viewModel.getAllCountries().map { if (it.States != null) states.addAll(it.States!!) }
        val countries :  ArrayList<State>  = ArrayList(states.filter { it.name!!.toLowerCase().contains(text.toLowerCase()) })
        adapter!!.notifyChange(countries)
        binding.progressBar.visibility = View.GONE
    }

    private fun subscribeUi() {
        viewModel.getCountries().observe(this) { apiResponse ->
            binding.progressBar.visibility = View.GONE
            val countries : ArrayList<State> = arrayListOf()
            apiResponse.map { if (it.States != null) countries.addAll(it.States!!) }
            adapter!!.notifyChange(countries)
        }
    }

    private fun onClickListener(state: State, position: Int) {
        val intent = Intent()
        intent.putExtra("state", state)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun onBackClick(view: View) {
        finish()
    }
}
