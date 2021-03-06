package ash.pickshunter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import ash.pickshunter.*
import ash.pickshunter.adapter.BrandAdapter
import ash.pickshunter.model.Manufacturer
import ash.pickshunter.model.User
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.UserViewModel
import ash.pickshunter.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_choose_brand.*

class ChooseBrandActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var brandAdapter: BrandAdapter
    lateinit var user: User

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_brand)
        btn_next.setOnClickListener(this)
        brandAdapter = BrandAdapter(arrayListOf(), ::onClickListener)
        rv_brands.adapter = brandAdapter
        user = PreferenceHelper(this).user
    }

    private fun onClickListener(manufacturer: Manufacturer, position: Int) {
        viewModel.updateSelectedBrand(position)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        ProgressDialog.show(this, false)
        viewModel.getBrands().observe(this) {
            ProgressDialog.dismiss()
            brandAdapter.notifyChange(it.manufacturers!!)
        }
    }

    override fun onClick(p0: View?) {
        when(p0) {
            btn_next -> {
                saveBrands()
            }
        }
    }

    private fun saveBrands() {
        var ids = ""
        ids = viewModel.getBrandsList().filter { it.selected }.joinToString(separator = ",") { it.id.toString() }
        if (ids == "") {
            Toast.makeText(this, "Please Choose Interests", Toast.LENGTH_LONG).show()
            return
        }
        ProgressDialog.show(this, false)
        viewModel.saveBrands(ids, user.id).observe(this) {
            ProgressDialog.dismiss()
            startActivity(Intent(this, ChooseCategoryActivity::class.java))
        }
    }
}
