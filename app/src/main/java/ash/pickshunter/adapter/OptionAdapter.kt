package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.Option
import ash.pickshunter.utils.inflateView
import kotlinx.android.synthetic.main.item_brand.view.*
import kotlinx.android.synthetic.main.item_option.view.*

class OptionAdapter(
    var options: List<Option>,
    val onClickListener: (Option, Int, Int) -> Unit,
    var pos: Int
) :
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
                holder.itemView.ll_option_bg.background.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.colorOrange
                    ), android.graphics.PorterDuff.Mode.MULTIPLY
                );


            } else {
                holder.itemView.ll_option_bg.background.clearColorFilter()
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