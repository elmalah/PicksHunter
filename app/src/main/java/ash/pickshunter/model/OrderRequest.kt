package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class OrderRequest {
    @SerializedName("order")
    var order: Order = Order()
}