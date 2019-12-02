package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Order() : Parcelable {
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

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        shippingRateComputationMethodSystemName = parcel.readString()
        shippingMethod = parcel.readString()
        paymentMethodSystemName = parcel.readString()
        customerId = parcel.readValue(Int::class.java.classLoader) as? Int
        orderTotal = parcel.readValue(Double::class.java.classLoader) as? Double
        billingAddressId = parcel.readValue(Int::class.java.classLoader) as? Int
        shippingAddressId = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(shippingRateComputationMethodSystemName)
        parcel.writeString(shippingMethod)
        parcel.writeString(paymentMethodSystemName)
        parcel.writeValue(customerId)
        parcel.writeValue(orderTotal)
        parcel.writeValue(billingAddressId)
        parcel.writeValue(shippingAddressId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
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