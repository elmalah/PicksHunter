package ash.pickshunter

import com.google.gson.annotations.SerializedName

class Order {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("shipping_rate_computation_method_system_name")
    var shippingRateComputationMethodSystemName: String? = null

    @SerializedName("shipping_method")
    var shippingMethod: String? = null

    @SerializedName("payment_method_system_name")
    var paymentMethodSystemName: String? = null

    @SerializedName("customer_id")
    var customerId: Int? = null

    @SerializedName("order_total")
    var orderTotal: Double? = null

    @SerializedName("billing_address_id")
    var billingAddressId: Int? = null

    @SerializedName("shipping_address_id")
    var shippingAddressId: Int? = null

    @SerializedName("order_items")
    var orderItems: ArrayList<OrderItem>? = arrayListOf()
}

class OrderItem {
    @SerializedName("quantity")
    var quantity: Int? = null

    @SerializedName("product_id")
    var productId: Int? = null

    @SerializedName("product_attributes")
    var productAttributes: ArrayList<ProductAttributeItem>? = arrayListOf()
}

class ProductAttributeItem{
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("value")
    var value: Int? = null

}