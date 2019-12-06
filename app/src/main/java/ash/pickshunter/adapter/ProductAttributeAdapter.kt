package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.ProductAttributeOption
import ash.pickshunter.model.ProductAttribute
import ash.pickshunter.utils.inflateView
import kotlinx.android.synthetic.main.item_product_attributes.view.*

class ProductAttributeAdapter(
    var attributes: List<ProductAttribute>,
    val onOptionClickListener: (ProductAttributeOption, Int, Int) -> Unit
) :
    RecyclerView.Adapter<ProductAttributeAdapter.ProductAttributeViewHolder>() {

    fun notifyChange(attributes: List<ProductAttribute>) {
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

            val adapter = ProductAttributeOptionAdapter(it.options!!, ::onClickListener, position)

            holder.itemView.rv_product_attributes.adapter = adapter
        }
    }

    private fun onClickListener(option: ProductAttributeOption, position: Int, attributePos: Int) {
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