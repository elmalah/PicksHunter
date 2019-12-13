package ash.pickshunter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import ash.pickshunter.*
import ash.pickshunter.adapter.CategoryAdapter
import ash.pickshunter.model.Category
import ash.pickshunter.model.User
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.UserViewModel
import ash.pickshunter.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_choose_category.*

class ChooseCategoryActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var user: User

    private val viewModel: UserViewModel by viewModels {
        InjectorUtils.provideUserViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_category)
        categoryAdapter = CategoryAdapter(arrayListOf(), ::onClickListener)
        rv_categories.adapter = categoryAdapter
        btn_next.setOnClickListener(this)
        user = PreferenceHelper(this).user
    }

    private fun onClickListener(category: Category, position: Int) {
        viewModel.updateSelectedCategory(position)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        ProgressDialog.show(this, false)
        viewModel.getCategories().observe(this) {
            ProgressDialog.dismiss()
            categoryAdapter.notifyChange(it.categories!!)
        }
    }

    override fun onClick(p0: View?) {
        when(p0) {
            btn_next -> {
                saveCategories()
            }
        }
    }

    private fun saveCategories() {
        var ids = ""
        ids = viewModel.getCategoriesList().filter { it.selected }.joinToString(separator = ",") { it.id.toString() }
        if (ids == "") {
            Toast.makeText(this, "Please Choose Interests", Toast.LENGTH_LONG).show()
            return
        }
        ProgressDialog.show(this, false)
        viewModel.saveCategories(ids, user.id).observe(this) {
            ProgressDialog.dismiss()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finishAffinity()
        }
    }
}
