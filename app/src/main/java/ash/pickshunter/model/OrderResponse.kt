package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class OrderResponse {

    @SerializedName("orders")
    var orders: ArrayList<Order>? = null
}