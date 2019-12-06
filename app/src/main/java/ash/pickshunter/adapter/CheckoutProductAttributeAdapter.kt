package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.ProductAttributeOption
import ash.pickshunter.model.ProductAttribute
import ash.pickshunter.model.ProductAttributeDetailed
import ash.pickshunter.model.ProductAttributeDetailedOption
import ash.pickshunter.utils.inflateView
import kotlinx.android.synthetic.main.item_product_attributes.view.*

class CheckoutProductAttributeAdapter(
    var attributes: List<ProductAttributeDetailed>,
    val onOptionClickListener: (ProductAttributeDetailedOption, Int, Int) -> Unit
) :
    RecyclerView.Adapter<CheckoutProductAttributeAdapter.ProductAttributeViewHolder>() {

    fun notifyChange(attributes: List<ProductAttributeDetailed>) {
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

            val adapter = CheckoutProductAttributeOptionAdapter(it.options!!, ::onClickListener, position)

            holder.itemView.rv_product_attributes.adapter = adapter
        }
    }

    private fun onClickListener(option: ProductAttributeDetailedOption, position: Int, attributePos: Int) {
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