package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.AttributeOptionView
import ash.pickshunter.utils.inflateView
import kotlinx.android.synthetic.main.item_attributes.view.*

class AttributeViewAdapter(var attributes: List<AttributeOptionView>) :
    RecyclerView.Adapter<AttributeViewAdapter.BrandViewHolder>() {

    fun notifyChange(attributes: List<AttributeOptionView>) {
        this.attributes = attributes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(parent.inflateView(R.layout.item_attributes))
    }

    override fun getItemCount(): Int = attributes.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        attributes[position].let {

            holder.itemView.tv_name.text = it.name.toString()
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