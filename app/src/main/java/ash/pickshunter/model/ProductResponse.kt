package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class ProductResponse {

    @SerializedName("products")
    var products: ArrayList<Product>? = null
}