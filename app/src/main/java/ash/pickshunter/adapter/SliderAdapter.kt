package ash.pickshunter.adapter

import android.content.Context
import android.widget.TextView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import ash.pickshunter.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SliderAdapter(
    var images: List<String>,
    var showDelete: Boolean,
    val onClickListener: (String, Int) -> Unit
) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    fun notifyChange(images: ArrayList<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_slider_image, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {

        if (showDelete)
            viewHolder.fabDelete.show()
        else
            viewHolder.fabDelete.hide()

        images[position].let {
            Glide.with(viewHolder.itemView)
                .load(it)
                .into(viewHolder.imageViewBackground)
        }

        viewHolder.fabDelete.setOnClickListener {
            onClickListener.invoke(images[position], position)
        }

    }

    override fun getCount(): Int = images.size

    inner class SliderAdapterVH(var itemView: View) :
        SliderViewAdapter.ViewHolder(itemView) {
        var imageViewBackground: ImageView
        var fabDelete: FloatingActionButton

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
            fabDelete = itemView.findViewById(R.id.fab_delete)
        }
    }
}

