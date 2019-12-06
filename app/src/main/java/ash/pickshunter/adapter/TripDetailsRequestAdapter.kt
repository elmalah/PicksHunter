package ash.pickshunter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.OrderView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_trip_details_request.view.*

class TripDetailsRequestAdapter(
    private val requests: ArrayList<OrderView>,
    val onClickListener: (OrderView, Int) -> Unit
) :
    RecyclerView.Adapter<TripDetailsRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trip_details_request, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val request = requests[position]

        Picasso.get().load(request.productImage)
            .placeholder(R.drawable.placeholder).into(holder.itemView.iv_product_image)

        holder.itemView.tv_product_name.text = request.productName

        holder.itemView.tv_order_status.text = request.orderStatus
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        val tv_request_title: TextView = itemView.child_textView
//        val iv_request_image: ImageView = itemView.child_imageView
//        val tv_coments_count: TextView = itemView.tv_comments_count

        init {
            itemView.setOnClickListener {
                onClickListener.invoke(requests[adapterPosition], adapterPosition)
            }
        }

    }
}
