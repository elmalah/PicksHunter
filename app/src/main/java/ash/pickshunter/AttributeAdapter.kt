package ash.pickshunter

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.country.Option
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_brand.view.*
import kotlinx.android.synthetic.main.item_specification_attributes.view.*
import kotlinx.android.synthetic.main.item_store.view.*

class AttributeAdapter(var attributes: List<Attribute>,
                       val onOptionSelectedListener: (Option, Int, Int) -> Unit) :
    RecyclerView.Adapter<AttributeAdapter.BrandViewHolder>() {

    fun notifyChange(attributes: List<Attribute>) {
        this.attributes = attributes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(parent.inflateView(R.layout.item_specification_attributes))
    }

    override fun getItemCount(): Int = attributes.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        attributes[position].let {

            holder.itemView.tv_spinner_name.text = it.name

            val adapter : ArrayAdapter<Option> =  ArrayAdapter<Option>(holder.itemView.context, android.R.layout.simple_spinner_item, it.options)
            holder.itemView.spinner_category.adapter = adapter
            holder.itemView.spinner_category.setSelection(0)

            holder.itemView.spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>,
                    selectedItemView: View,
                    pos: Int,
                    id: Long
                ) {
                    onOptionSelectedListener.invoke(attributes[position].options!![pos], pos, position)
                }

                override fun onNothingSelected(parentView: AdapterView<*>) {
                    // your code here
                }

            }
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