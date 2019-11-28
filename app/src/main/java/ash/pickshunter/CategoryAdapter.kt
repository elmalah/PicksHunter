package ash.pickshunter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(var categories: List<Category>,
                      val onClickListener: (Category, Int) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    fun notifyChange(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(parent.inflateView(R.layout.item_category))
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        categories[position].let {
            Picasso.get().load(it.Image!!.src)
                .placeholder(R.drawable.loginlogo).into(holder.itemView.iv_category)

            holder.itemView.tv_title.text = it.name

            if (it.selected) {
                holder.itemView.ll_bg.backgroundTintList =
                    holder.itemView.resources.getColorStateList(R.color.colorOrange)
                holder.itemView.iv_select.visibility = View.VISIBLE
            }
            else {
                holder.itemView.ll_bg.backgroundTintList =
                    holder.itemView.resources.getColorStateList(R.color.colorGray2)
                holder.itemView.iv_select.visibility = View.INVISIBLE
            }
        }
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickListener.invoke(categories[adapterPosition], adapterPosition)
            }
        }
    }
}