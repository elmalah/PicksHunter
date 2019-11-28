package ash.pickshunter.country

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ash.pickshunter.Country
import ash.pickshunter.R
import ash.pickshunter.State
import ash.pickshunter.inflateView
import kotlinx.android.synthetic.main.item_country.view.*

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