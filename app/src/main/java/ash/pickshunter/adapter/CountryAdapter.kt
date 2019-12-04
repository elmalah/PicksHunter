package ash.pickshunter.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.R
import ash.pickshunter.model.State
import ash.pickshunter.utils.inflateView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_country.view.*
import kotlinx.android.synthetic.main.item_country.view.iv_country_flag

class CountryAdapter(
    var countries: List<State>,
    val onClickListener: (State, Int) -> Unit
) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun notifyChange(countries: ArrayList<State>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(parent.inflateView(R.layout.item_country))
    }

    override fun getItemCount(): Int = countries.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        countries[position].let {

            holder.itemView.tv_country_name.text = it.name

            Picasso.get().load(it.flag)
                .placeholder(R.drawable.placeholder).into(holder.itemView.iv_country_flag)
        }
    }

    inner class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickListener.invoke(countries[adapterPosition], adapterPosition)
            }
        }
    }
}