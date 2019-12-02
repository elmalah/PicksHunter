package ash.pickshunter.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.*
import ash.pickshunter.model.Shop
import ash.pickshunter.utils.inflateView
import kotlinx.android.synthetic.main.item_country.view.*

class ShopAdapter(
    var shops: List<Shop>,
    val onClickListener: (Shop, Int) -> Unit
) :
    RecyclerView.Adapter<ShopAdapter.CountryViewHolder>() {

    fun notifyChange(shops: ArrayList<Shop>) {
        this.shops = shops
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(parent.inflateView(R.layout.item_country))
    }

    override fun getItemCount(): Int = shops.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        shops[position].let {

            holder.itemView.tv_country_name.text = it.name
        }
    }

    inner class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickListener.invoke(shops[adapterPosition], adapterPosition)
            }
        }
    }
}