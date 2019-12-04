package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.model.Attribute
import ash.pickshunter.R
import ash.pickshunter.model.Option
import ash.pickshunter.utils.inflateView
import kotlinx.android.synthetic.main.item_product_attributes.view.*

class ProductAttributeAdapter(var attributes: List<Attribute>,
                              val onOptionClickListener: (Option, Int, Int) -> Unit) :
    RecyclerView.Adapter<ProductAttributeAdapter.ProductAttributeViewHolder>() {

    fun notifyChange(attributes: List<Attribute>) {
        this.attributes = attributes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAttributeViewHolder {
        return ProductAttributeViewHolder(parent.inflateView(R.layout.item_product_attributes))
    }

    override fun getItemCount(): Int = attributes.size

    override fun onBindViewHolder(holder: ProductAttributeViewHolder, position: Int) {
        attributes[position].let {

            holder.itemView.tv_name.text = it.name

            val adapter =
                OptionAdapter(it.options!!, ::onClickListener, position)

            holder.itemView.rv_attributes.adapter = adapter
        }
    }

    private fun onClickListener(option: Option, position: Int, attributePos: Int) {
        onOptionClickListener.invoke(option, position, attributePos)
    }

    inner class ProductAttributeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
//            view..setOnClickListener {
//                onClickListener.invoke(shops[adapterPosition], adapterPosition, false)
//            }
//            view.cl_store.setOnClickListener {
//                onClickListener.invoke(shops[adapterPosition], adapterPosition, true)
//            }
        }
    }
}