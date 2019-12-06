package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

public class OrderView() : Parcelable {

    @SerializedName("OrderId")
    var orderId: Int? = null

    @SerializedName("OrderCustomNumber")
    var orderCustomNumber: String? = null

    @SerializedName("OrderFriendlyDate")
    var orderFriendlyDate: String? = null

    @SerializedName("ProductId")
    var productId: Int? = null

    @SerializedName("ProductName")
    var productName: String? = null

    @SerializedName("ProductImage")
    var productImage: String? = null

    @SerializedName("ProductAttributes")
    var productAttributes: ArrayList<OrderProductAttribute>? = null

    @SerializedName("OrderStatusId")
    var orderStatusId: Int? = null

    @SerializedName("OrderStatus")
    var orderStatus: String? = null

    @SerializedName("OrderRejectionReasonId")
    var orderRejectionReasonId: Int? = null

    @SerializedName("OrderRejectionReason")
    var orderRejectionReason: String? = null

    @SerializedName("CustomerName")
    var customerName: String? = null

    @SerializedName("CustomerAvatar")
    var customerAvatar: String? = null

    @SerializedName("TotalPrice")
    var totalPrice: String? = null

    @SerializedName("Commission")
    var commission: String? = null

    @SerializedName("HunterName")
    var hunterName: String? = null

    @SerializedName("Flag")
    var flag: String? = null

    @SerializedName("Product")
    var product: ProductView? = null

    constructor(parcel: Parcel) : this() {
        orderId = parcel.readValue(Int::class.java.classLoader) as? Int
        orderCustomNumber = parcel.readString()
        orderFriendlyDate = parcel.readString()
        productId = parcel.readValue(Int::class.java.classLoader) as? Int
        productName = parcel.readString()
        productImage = parcel.readString()
        orderStatusId = parcel.readValue(Int::class.java.classLoader) as? Int
        orderStatus = parcel.readString()
        orderRejectionReasonId = parcel.readValue(Int::class.java.classLoader) as? Int
        orderRejectionReason = parcel.readString()
        customerName = parcel.readString()
        customerAvatar = parcel.readString()
        totalPrice = parcel.readString()
        commission = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(orderId)
        parcel.writeString(orderCustomNumber)
        parcel.writeString(orderFriendlyDate)
        parcel.writeValue(productId)
        parcel.writeString(productName)
        parcel.writeString(productImage)
        parcel.writeValue(orderStatusId)
        parcel.writeString(orderStatus)
        parcel.writeValue(orderRejectionReasonId)
        parcel.writeString(orderRejectionReason)
        parcel.writeString(customerName)
        parcel.writeString(customerAvatar)
        parcel.writeString(totalPrice)
        parcel.writeString(commission)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderView> {
        override fun createFromParcel(parcel: Parcel): OrderView {
            return OrderView(parcel)
        }

        override fun newArray(size: Int): Array<OrderView?> {
            return arrayOfNulls(size)
        }
    }


}

