package ash.pickshunter

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.country.Option
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_brand.view.*
import kotlinx.android.synthetic.main.item_option.view.*
import kotlinx.android.synthetic.main.item_specification_attributes.view.*
import kotlinx.android.synthetic.main.item_store.view.*
import kotlinx.android.synthetic.main.item_option.view.ll_bg as ll_bg1

class OptionAdapter(var options: List<Option>,
                    val onClickListener: (Option, Int, Int) -> Unit,
                    var pos: Int) :
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    fun notifyChange(options: List<Option>) {
        this.options = options
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder(parent.inflateView(R.layout.item_option))
    }

    override fun getItemCount(): Int = options.size

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        options[position].let {

            holder.itemView.iv_option.text = if (it.name != null) it.name else it.value

            if (it.selected) {
                holder.itemView.ll_bg.backgroundTintList =
                    holder.itemView.resources.getColorStateList(R.color.colorOrange)
            }
            else {
                holder.itemView.ll_bg.backgroundTintList =
                    holder.itemView.resources.getColorStateList(R.color.colorGray2)
            }
        }
    }

    inner class OptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickListener.invoke(options[adapterPosition], adapterPosition, pos)
            }
//            view.cl_store.setOnClickListener {
//                onClickListener.invoke(shops[adapterPosition], adapterPosition, true)
//            }
        }
    }
}