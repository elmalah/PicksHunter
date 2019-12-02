package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProductView() : Parcelable {

    @SerializedName("ProductId")
    var productId: Int? = null

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

    @SerializedName("ShoperAvatar")
    var shoperAvatar: String? = null

    @SerializedName("Comments")
    var comments: ArrayList<Comment>? = null

    @SerializedName("SpecificationAttributes")
    var specificationAttributes: ArrayList<Option>? = null

    @SerializedName("ProductAttributes")
    var productAttributes: ArrayList<Option>? = null

    @SerializedName("ProductAttributesDetailed")
    var productAttributesDetailed: ArrayList<Attribute>? = null

    constructor(parcel: Parcel) : this() {
        //productId = parcel.readValue(Int::class.java.classLoader) as? Int
        productId = parcel.readInt()

        //shopperName = parcel.readString()
        //productName = parcel.readString()
        //date = parcel.readString()
        //fromCountryAndState = parcel.readString()
        //toCountryAndState = parcel.readString()
        //tripCountryFlag = parcel.readString()
        //fromDate = parcel.readString()
        //toDate = parcel.readString()
        //shopName = parcel.readString()
        //description = parcel.readString()
        //price = parcel.readString()
        //shoperAvatar = parcel.readString()
        //productAttributesDetailed =
        //    parcel.readValue(Array<Attribute>::class.java.classLoader) as? java.util.ArrayList<Attribute>
        //productAttributes =
        //    parcel.readValue(Array<Option>::class.java.classLoader) as? java.util.ArrayList<Option>
        //specificationAttributes =
        //    parcel.readValue(Array<Option>::class.java.classLoader) as? java.util.ArrayList<Option>
        //comments =
        //    parcel.readValue(Array<Comment>::class.java.classLoader) as? java.util.ArrayList<Comment>
        //shoperAvatar = parcel.readString()
        //displayPrice = parcel.readString()
        //price = parcel.readString()
        //description = parcel.readString()
        //productImages =
        //    parcel.readValue(Array<String>::class.java.classLoader) as? java.util.ArrayList<String>
        //shopName = parcel.readString()
        //returnsIn = parcel.readString()
        //toDate = parcel.readString()
        //fromDate = parcel.readString()
        //tripCountryFlag = parcel.readString()
        //toCountryAndState = parcel.readString()
        //fromCountryAndState = parcel.readString()
        //date = parcel.readString()
        //productName = parcel.readString()
        //shopperName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(productId)

        //parcel.writeValue(productAttributesDetailed)
        //parcel.writeValue(productAttributes)
        //parcel.writeValue(specificationAttributes)
        //parcel.writeValue(comments)
        //parcel.writeString(shoperAvatar)
        //parcel.writeString(displayPrice)
        //parcel.writeString(price)
        //parcel.writeString(description)
        //parcel.writeValue(productImages)
        //parcel.writeString(shopName)
        //parcel.writeString(returnsIn)
        //parcel.writeString(toDate)
        //parcel.writeString(fromDate)
        //parcel.writeString(tripCountryFlag)
        //parcel.writeString(toCountryAndState)
        //parcel.writeString(fromCountryAndState)
        //parcel.writeString(date)
        //parcel.writeString(productName)
        //parcel.writeString(shopperName)
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