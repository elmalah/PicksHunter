package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProductView() : Parcelable {

    @SerializedName("ProductId")
    var productId: Int? = null

    @SerializedName("ShopperCustomerId")
    var shopperCustomerId: Int? = null

    @SerializedName("ShopperName")
    var shopperName: String? = null

    @SerializedName("ProductName")
    var productName: String? = null

    @SerializedName("Category")
    var category: String? = null

    @SerializedName("Date")
    var date: String? = null

    @SerializedName("FromCountryAndState")
    var fromCountryAndState: String? = null

    @SerializedName("ToCountryAndState")
    var toCountryAndState: String? = null

    @SerializedName("TripCountryFlag")
    var tripCountryFlag: String? = null

    @SerializedName("FromDate")
    var fromDate: String? = null

    @SerializedName("ToDate")
    var toDate: String? = null

    @SerializedName("ReturnsIn")
    var returnsIn: String? = null

    @SerializedName("ShopName")
    var shopName: String? = null

    @SerializedName("ProductImages")
    var productImages: ArrayList<String>? = null

    @SerializedName("Description")
    var description: String? = null

    @SerializedName("Price")
    var price: Double? = null

    @SerializedName("DisplayPrice")
    var displayPrice: String? = null

    @SerializedName("ShopperAvatar")
    var shopperAvatar: String? = null

    @SerializedName("Comments")
    var comments: ArrayList<Comment>? = null

    @SerializedName("SpecificationAttributes")
    var specificationAttributes: ArrayList<AttributeOptionView>? = null

    @SerializedName("ProductAttributes")
    var productAttributes: ArrayList<AttributeOptionView>? = null

    @SerializedName("ProductAttributesDetailed")
    var productAttributesDetailed: ArrayList<ProductAttributeDetailed>? = null

    constructor(parcel: Parcel) : this() {
        productId = parcel.readValue(Int::class.java.classLoader) as? Int
        shopperCustomerId = parcel.readValue(Int::class.java.classLoader) as? Int
        shopperName = parcel.readString()
        productName = parcel.readString()
        category = parcel.readString()
        date = parcel.readString()
        fromCountryAndState = parcel.readString()
        toCountryAndState = parcel.readString()
        tripCountryFlag = parcel.readString()
        fromDate = parcel.readString()
        toDate = parcel.readString()
        returnsIn = parcel.readString()
        shopName = parcel.readString()
        description = parcel.readString()
        price = parcel.readValue(Double::class.java.classLoader) as? Double
        displayPrice = parcel.readString()
        shopperAvatar = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(productId)
        parcel.writeValue(shopperCustomerId)
        parcel.writeString(shopperName)
        parcel.writeString(productName)
        parcel.writeString(category)
        parcel.writeString(date)
        parcel.writeString(fromCountryAndState)
        parcel.writeString(toCountryAndState)
        parcel.writeString(tripCountryFlag)
        parcel.writeString(fromDate)
        parcel.writeString(toDate)
        parcel.writeString(returnsIn)
        parcel.writeString(shopName)
        parcel.writeString(description)
        parcel.writeValue(price)
        parcel.writeString(displayPrice)
        parcel.writeString(shopperAvatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductView> {
        override fun createFromParcel(parcel: Parcel): ProductView {
            return ProductView(parcel)
        }

        override fun newArray(size: Int): Array<ProductView?> {
            return arrayOfNulls(size)
        }
    }


}