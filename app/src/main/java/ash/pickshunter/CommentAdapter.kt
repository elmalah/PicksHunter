package ash.pickshunter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_brand.view.*
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(var comments: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.BrandViewHolder>() {

    fun notifyChange(comments: ArrayList<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    fun notifyChangePosition(brands: List<Comment>, position: Int) {
        this.comments = comments
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        return BrandViewHolder(parent.inflateView(R.layout.item_comment))
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        comments[position].let {
//            Picasso.get().load(it.Image!!.src)
//                .placeholder(R.drawable.loginlogo).into(holder.itemView.iv_brand)

            holder.itemView.tv_name.text = it.customerName

            holder.itemView.tv_comment.text = it.comment

            holder.itemView.tv_time.text = it.friendlyDate

        }
    }

    inner class BrandViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
//            view.setOnClickListener {
//                onClickListener.invoke(comments[adapterPosition], adapterPosition)
//            }
        }
    }
}