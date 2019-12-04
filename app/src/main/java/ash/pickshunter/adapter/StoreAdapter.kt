package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.Shop
import ash.pickshunter.utils.inflateView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_store.view.*

class StoreAdapter(var shops: List<Shop>,
                   val onClickListener: (Shop, Int, Boolean) -> Unit) :
    RecyclerView.Adapter<StoreAdapter.BrandViewHolder>() {

    fun notifyChange(shops: List<Shop>) {
        this.shops = shops
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(parent.inflateView(R.layout.item_store))
    }

    override fun getItemCount(): Int = shops.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        shops[position].let {

            holder.itemView.tv_store_name.text = it.name
            Picasso.get().load(it.logo)
                .placeholder(R.drawable.loginlogo).into(holder.itemView.iv_store)

        }
    }

    inner class BrandViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.ll_check.setOnClickListener {
                onClickListener.invoke(shops[adapterPosition], adapterPosition, false)
            }
            view.ll_add.setOnClickListener {
                onClickListener.invoke(shops[adapterPosition], adapterPosition, true)
            }
        }
    }
}