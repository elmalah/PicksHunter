package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.model.Manufacturer
import ash.pickshunter.R
import ash.pickshunter.utils.inflateView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*

class ManufacturerAdapter(
    var manufacturers: List<Manufacturer>,
    val onClickListener: (Manufacturer, Int) -> Unit
) :
    RecyclerView.Adapter<ManufacturerAdapter.ManufacturerViewHolder>() {

    fun notifyChange(manufacturers: List<Manufacturer>) {
        this.manufacturers = manufacturers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerViewHolder {
        return ManufacturerViewHolder(parent.inflateView(R.layout.item_category))
    }

    override fun getItemCount(): Int = manufacturers.size

    override fun onBindViewHolder(holder: ManufacturerViewHolder, position: Int) {
        manufacturers[position].let {
            Picasso.get().load(it.Image!!.src)
                .placeholder(R.drawable.placeholder).into(holder.itemView.iv_category)

            holder.itemView.tv_title.text = it.name

            if (it.selected) {
                holder.itemView.ll_bg.backgroundTintList =
                    holder.itemView.resources.getColorStateList(R.color.colorOrange)
                holder.itemView.iv_select.visibility = View.VISIBLE
            } else {
                holder.itemView.ll_bg.backgroundTintList =
                    holder.itemView.resources.getColorStateList(R.color.colorGray2)
                holder.itemView.iv_select.visibility = View.INVISIBLE
            }
        }
    }

    inner class ManufacturerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickListener.invoke(manufacturers[adapterPosition], adapterPosition)
            }
        }
    }
}