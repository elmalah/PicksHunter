package ash.pickshunter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.ProductView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_trip_details_product.view.*

class TripDetailsProductAdapter(
    private val products: ArrayList<ProductView>,
    val onClickListener: (ProductView, Int) -> Unit
) :
    RecyclerView.Adapter<TripDetailsProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trip_details_product, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        Picasso.get().load(product.productImages?.getOrNull(0)!!)
            .placeholder(R.drawable.placeholder).into(holder.iv_product_image)

        holder.tv_product_title.text = product.productName

        holder.tv_coments_count.text = product.comments?.count().toString()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv_product_title: TextView = itemView.child_textView
        val iv_product_image: ImageView = itemView.child_imageView
        val tv_coments_count: TextView = itemView.tv_comments_count

        init {
            itemView.setOnClickListener {
                onClickListener.invoke(products[adapterPosition], adapterPosition)
            }
        }

    }
}
