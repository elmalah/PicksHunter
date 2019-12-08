package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.SpecificationAttributeOption
import ash.pickshunter.model.SpecificationAttribute
import ash.pickshunter.utils.inflateView
import kotlinx.android.synthetic.main.item_specification_attributes.view.*

class SpecificationAttributeAdapter(
    var attributes: List<SpecificationAttribute>,
    val onOptionSelectedListener: (SpecificationAttributeOption, Int, Int) -> Unit
) :
    RecyclerView.Adapter<SpecificationAttributeAdapter.BrandViewHolder>() {

    fun notifyChange(attributes: List<SpecificationAttribute>) {
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

            var options = it.options!!.map {
                it.name
            }.toTypedArray()

            val adapter: ArrayAdapter<String?> =
                ArrayAdapter(holder.itemView.context, android.R.layout.simple_spinner_item, options)


            holder.itemView.spinner_specification_attribute.adapter = adapter
            holder.itemView.spinner_specification_attribute.setSelection(0)

            holder.itemView.spinner_specification_attribute.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parentView: AdapterView<*>,
                        selectedItemView: View,
                        pos: Int,
                        id: Long
                    ) {
                        onOptionSelectedListener.invoke(
                            attributes[position].options!![pos],
                            pos,
                            position
                        )
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