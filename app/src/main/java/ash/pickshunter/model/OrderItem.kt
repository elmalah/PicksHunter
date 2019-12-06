package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class OrderItem {
    @SerializedName("quantity")
    var quantity: Int? = null

    @SerializedName("product_id")
    var productId: Int? = null

    @SerializedName("product_attributes")
    var productAttributes: ArrayList<ProductAttributeItem>? = arrayListOf()
}