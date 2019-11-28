package ash.pickshunter

import com.google.gson.annotations.SerializedName

class PriceRequest {

    @SerializedName("price")
    var price: Price = Price()
}