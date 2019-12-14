package ash.pickshunter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.model.OrderView
import ash.pickshunter.R
import ash.pickshunter.utils.inflateView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.tv_product_title
import kotlinx.android.synthetic.main.item_request.view.*

class LiveTripsAdapter(
    var orders: List<OrderView>,
    val onClickListener: (OrderView, Int, Boolean) -> Unit
) :
    RecyclerView.Adapter<LiveTripsAdapter.OrderViewHolder>() {

    fun notifyChange(orders: List<OrderView>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(parent.inflateView(R.layout.live_trips_list_item))
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
//        orders[position].let {
//            val order = it
//
//            Picasso.get().load(it.productImage)
//                .placeholder(R.drawable.placeholder).into(holder.itemView.iv_product_img)
//            Picasso.get().load(it.customerAvatar)
//                .placeholder(R.drawable.placeholder).into(holder.itemView.iv_avatar)
//
//            holder.itemView.tv_customer_name.text = order.customerName
//            holder.itemView.tv_order_date.text = order.orderFriendlyDate
//            holder.itemView.tv_product_title.text = order.productName
//            holder.itemView.tv_request_id_value.text = order.orderCustomNumber
//            holder.itemView.tv_product_id_value.text = order.productId.toString()
//            holder.itemView.tv_total_price_value.text = order.totalPrice
//            holder.itemView.tv_comission_value.text = order.commission
//
//            val firstSize = order.productAttributes?.getOrNull(0)
//            if (firstSize != null) {
//                holder.itemView.tv_size.text = firstSize.key
//                holder.itemView.tv_size_id_value.text = firstSize.value
//            }
//
//            if (order.orderStatusId != 1) {
//                holder.itemView.ll_buttons.visibility = View.GONE
//            }
//
//            holder.itemView.tv_order_status.text =
//                order.orderStatus + if (order.orderRejectionReasonId != 0) {
//                    " (" + order.orderRejectionReason + ")"
//                } else {
//                    ""
//                }
//        }
    }

    inner class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        init {
//            view.ll_reject.setOnClickListener() {
//                onClickListener.invoke(orders[adapterPosition], adapterPosition, false)
//            }
//            view.ll_done.setOnClickListener {
//                onClickListener.invoke(orders[adapterPosition], adapterPosition, true)
//            }
//        }
    }
}