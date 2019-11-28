package ash.pickshunter

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.country.Option
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_attributes.view.*
import kotlinx.android.synthetic.main.item_brand.view.*
import kotlinx.android.synthetic.main.item_specification_attributes.view.*
import kotlinx.android.synthetic.main.item_store.view.*

class AttributeViewAdapter(var attributes: List<Option>) :
    RecyclerView.Adapter<AttributeViewAdapter.BrandViewHolder>() {

    fun notifyChange(attributes: List<Option>) {
        this.attributes = attributes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(parent.inflateView(R.layout.item_attributes))
    }

    override fun getItemCount(): Int = attributes.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        attributes[position].let {

            holder.itemView.tv_name.text = it.name
            holder.itemView.tv_value.text = it.value


        }
    }

    inner class BrandViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
//            view.ll_check.setOnClickListener {
//                onClickListener.invoke(shops[adapterPosition], adapterPosition, false)
//            }
//            view.cl_store.setOnClickListener {
//                onClickListener.invoke(shops[adapterPosition], adapterPosition, true)
//            }
        }
    }
}