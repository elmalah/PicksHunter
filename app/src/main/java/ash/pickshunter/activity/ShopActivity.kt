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
import ash.pickshunter.*
import ash.pickshunter.databinding.ActivityCountryBinding
import ash.pickshunter.adapter.ShopAdapter
import ash.pickshunter.model.Shop
import ash.pickshunter.viewModel.TripViewModel
import com.fly365.utils.injection.InjectorUtils
import kotlinx.android.synthetic.main.activity_country.*

class ShopActivity : AppCompatActivity() {

    private var adapter: ShopAdapter? = null
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
            ShopAdapter(
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
        val states = viewModel.shops.value
        val countries :  ArrayList<Shop>  = ArrayList(states?.filter { it.name!!.toLowerCase().contains(text.toLowerCase()) })
        adapter!!.notifyChange(countries)
        binding.progressBar.visibility = View.GONE
    }

    private fun subscribeUi() {
        viewModel.getShops().observe(this) { apiResponse ->
            binding.progressBar.visibility = View.GONE
            adapter!!.notifyChange(apiResponse)
        }
    }

    private fun onClickListener(shop: Shop, position: Int) {
        val intent = Intent()
        intent.putExtra("shop", shop)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun onBackClick(view: View) {
        finish()
    }
}
