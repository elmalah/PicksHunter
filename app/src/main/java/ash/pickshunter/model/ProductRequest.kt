package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class ProductRequest {

    @SerializedName("product")
    var product: Product = Product()
}