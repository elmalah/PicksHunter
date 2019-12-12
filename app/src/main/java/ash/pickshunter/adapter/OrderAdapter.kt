package ash.pickshunter.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ash.pickshunter.R


import ash.pickshunter.fragment.OrderFragment.OnListFragmentInteractionListener
import ash.pickshunter.model.OrderView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_order.view.*
import kotlinx.android.synthetic.main.item_order.view.iv_avatar
import kotlinx.android.synthetic.main.item_order.view.iv_product_img
import kotlinx.android.synthetic.main.item_order.view.tv_hunter_name
import kotlinx.android.synthetic.main.item_order.view.tv_price
import kotlinx.android.synthetic.main.item_order.view.tv_product_title
import kotlinx.android.synthetic.main.item_order.view.tv_size

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class OrderAdapter(
    private var orders: List<OrderView>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as OrderView
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    fun notifyChange(orders: List<OrderView>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
//        holder.mIdView.text = item.id
//        holder.mContentView.text = item.content

        Picasso.get().load(order.productImage)
            .placeholder(R.drawable.placeholder).into(holder.itemView.iv_product_img)
        Picasso.get().load(order.flag)
            .placeholder(R.drawable.placeholder).into(holder.itemView.iv_avatar)

        holder.itemView.tv_hunter_name.text = order.hunterName
        holder.itemView.tv_date.text = order.orderFriendlyDate
        holder.itemView.tv_product_title.text = order.productName
        holder.itemView.tv_price.text = order.totalPrice

        val firstSize = order.productAttributes?.getOrNull(0)
        if (firstSize != null) {
            holder.itemView.tv_size.text = firstSize.key + ": " + firstSize.value
        }

        holder.itemView.tv_status.text =
            order.orderStatus + if (order.orderRejectionReasonId != 0) {
                " (" + order.orderRejectionReason + ")"
            } else {
                ""
            }

        with(holder.mView) {
            tag = order
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = orders.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
//        val mIdView: TextView = mView.item_number
//        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString()
        }
    }
}
