package ash.pickshunter

import android.os.Parcel
import android.os.Parcelable
import ash.pickshunter.country.Option
import com.google.gson.annotations.SerializedName

class Product() : Parcelable {

    @SerializedName("ProductId")
    var productId: Int? = null

    @SerializedName("ShopperName")
    var shopperName: String? = null

    @SerializedName("ProductName")
    var productName: String? = null

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
    var toDate:String ? = null

    @SerializedName("ReturnsIn")
    var returnsIn:String ? = null

    @SerializedName("ShopName")
    var shopName: String? = null

    @SerializedName("ProductImages")
    var productImages: ArrayList<String>? = null

    @SerializedName("images")
    var images: ArrayList<ProductPicture>? = null

    @SerializedName("Description")
    var description: String? = null

    @SerializedName("price")
    var price: String? = null

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
    var ProductAttributesDetailed: ArrayList<Attribute>? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("short_description")
    var shortDescription: String? = null

    @SerializedName("category_ids")
    var categoryIds: ArrayList<Int>? = arrayListOf()

    @SerializedName("manufacturer_ids")
    var manufacturerIds: ArrayList<Int>? = arrayListOf()

    @SerializedName("product_specification_attributes")
    var productSpecificationAttributes: ArrayList<ProductSpecificationAttributes> = arrayListOf()

    @SerializedName("attributes")
    var attributes: ArrayList<AttributeRequest> = arrayListOf()

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("published")
    var published = false

//    @SerializedName("price")
//    var price: Double? = null

    @SerializedName("product_cost")
    var productCost: Double? = null

    constructor(parcel: Parcel) : this() {
        productId = parcel.readValue(Int::class.java.classLoader) as? Int
        shopperName = parcel.readString()
        productName = parcel.readString()
        date = parcel.readString()
        fromCountryAndState = parcel.readString()
        toCountryAndState = parcel.readString()
        tripCountryFlag = parcel.readString()
        fromDate = parcel.readString()
        toDate = parcel.readString()
        shopName = parcel.readString()
        description = parcel.readString()
        price = parcel.readString()
        shoperAvatar = parcel.readString()
        name = parcel.readString()
        shortDescription = parcel.readString()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        published = parcel.readByte() != 0.toByte()
        productCost = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(productId)
        parcel.writeString(shopperName)
        parcel.writeString(productName)
        parcel.writeString(date)
        parcel.writeString(fromCountryAndState)
        parcel.writeString(toCountryAndState)
        parcel.writeString(tripCountryFlag)
        parcel.writeString(fromDate)
        parcel.writeString(toDate)
        parcel.writeString(shopName)
        parcel.writeString(description)
        parcel.writeString(price)
        parcel.writeString(shoperAvatar)
        parcel.writeString(name)
        parcel.writeString(shortDescription)
        parcel.writeValue(id)
        parcel.writeByte(if (published) 1 else 0)
        parcel.writeValue(productCost)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }


}