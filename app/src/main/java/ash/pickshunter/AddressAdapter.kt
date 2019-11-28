package ash.pickshunter

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_address.view.*
import kotlinx.android.synthetic.main.item_brand.view.*

class AddressAdapter(var addresses: List<Address>,
                     val onClickListener: (Address, Int) -> Unit) :
    RecyclerView.Adapter<AddressAdapter.BrandViewHolder>() {

    fun notifyChange(addresses: List<Address>) {
        this.addresses = addresses
        notifyDataSetChanged()
    }

    fun notifyChangePosition(brands: List<Address>, position: Int) {
        this.addresses = addresses
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(parent.inflateView(R.layout.item_address))
    }

    override fun getItemCount(): Int = addresses.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        addresses[position].let {

            holder.itemView.tv_name.text = it.address1

            if (it.selected) {
                holder.itemView.tv_name.setTextColor(holder.itemView.context.resources.getColor(R.color.colorOrange))
                holder.itemView.iv_icon.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.colorOrange), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
            else {
                holder.itemView.tv_name.setTextColor(holder.itemView.context.resources.getColor(R.color.colorGray2))
                holder.itemView.iv_icon.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.colorGray2), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    inner class BrandViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickListener.invoke(addresses[adapterPosition], adapterPosition)
            }
        }
    }
}