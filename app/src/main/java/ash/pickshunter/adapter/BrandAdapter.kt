package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.model.Manufacturer
import ash.pickshunter.R
import ash.pickshunter.utils.inflateView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_brand.view.*

class BrandAdapter(
    var brands: List<Manufacturer>,
    val onClickListener: (Manufacturer, Int) -> Unit
) :
    RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    fun notifyChange(brands: List<Manufacturer>) {
        this.brands = brands
        notifyDataSetChanged()
    }

    fun notifyChangePosition(brands: List<Manufacturer>, position: Int) {
        this.brands = brands
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(parent.inflateView(R.layout.item_brand))
    }

    override fun getItemCount(): Int = brands.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        brands[position].let {
            Picasso.get().load(it.Image!!.src)
                .placeholder(R.drawable.loginlogo).into(holder.itemView.iv_brand)

            if (it.selected) {
                holder.itemView.ll_brand_bg.backgroundTintList =
                    holder.itemView.resources.getColorStateList(R.color.colorOrange)
                holder.itemView.iv_select.visibility = View.VISIBLE
            } else {
                holder.itemView.ll_brand_bg.backgroundTintList =
                    holder.itemView.resources.getColorStateList(R.color.colorGray2)
                holder.itemView.iv_select.visibility = View.INVISIBLE
            }
        }
    }

    inner class BrandViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickListener.invoke(brands[adapterPosition], adapterPosition)
            }
        }
    }
}