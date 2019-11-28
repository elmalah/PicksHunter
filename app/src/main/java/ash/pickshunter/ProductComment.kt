package ash.pickshunter

import com.google.gson.annotations.SerializedName

class ProductCommentRequest {

    @SerializedName("product-comment")
    var productComment: ProductComment = ProductComment()
}

class ProductComment {

    @SerializedName("customer-id")
    var customerId: Int? = null

    @SerializedName("product-id")
    var productId: Int? = null

    @SerializedName("comment")
    var comment: String? = null
}