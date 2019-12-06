package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.model.ProductView
import ash.pickshunter.R
import ash.pickshunter.utils.inflateView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(
    var products: List<ProductView>,
    val onClickListener: (ProductView, Int) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    fun notifyChange(products: List<ProductView>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(parent.inflateView(R.layout.item_product))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        products[position].let {

            holder.itemView.tv_check_post.text = it.date
            holder.itemView.tv_name.text = it.shopperName
            if (it.productImages != null && it.productImages!!.isNotEmpty())
                Picasso.get().load(it.productImages!![0])
                    .placeholder(R.drawable.placeholder).into(holder.itemView.iv_product_image)

            Picasso.get().load(it.tripCountryFlag)
                .placeholder(R.drawable.placeholder).into(holder.itemView.iv_country_flag)

            holder.itemView.tv_product_title.text = it.productName

            //holder.itemView.tv_from.text = it.fromDate + " " + it.fromCountryAndState
            //holder.itemView.tv_to.text = it.toDate + " " + it.toCountryAndState

            holder.itemView.tv_returnsIn.text = it.returnsIn

            holder.itemView.tv_brand_name.text = it.shopName

            holder.itemView.tv_desc.text = it.description

            holder.itemView.tv_category.text = it.category

            holder.itemView.tv_price.text = it.displayPrice

            holder.itemView.tv_comments_count.text = it.comments!!.count().toString()


            Picasso.get().load(it.shoperAvatar)
                .placeholder(R.drawable.placeholder).into(holder.itemView.iv_product)

        }
    }

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickListener.invoke(products[adapterPosition], adapterPosition)
            }
        }
    }
}