package ash.pickshunter.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflateView(layout: Int): View {
    val layoutInflater = LayoutInflater.from(this.context)
    return layoutInflater.inflate(layout, this, false)
}