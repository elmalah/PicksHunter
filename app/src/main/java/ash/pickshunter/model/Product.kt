package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Product() : Parcelable {

    @SerializedName("product_id")
    var productId: Int? = null

    @SerializedName("images")
    var images: ArrayList<ProductPicture>? = arrayListOf()

    @SerializedName("name")
    var name: String? = null

    @SerializedName("short_description")
    var shortDescription: String? = null

    @SerializedName("category_ids")
    var categoryIds: ArrayList<Int>? = arrayListOf()

    @SerializedName("manufacturer_ids")
    var manufacturerIds: ArrayList<Int>? = arrayListOf()

    @SerializedName("product_specification_attributes")
    var productSpecificationAttributes: ArrayList<ProductSpecificationAttributes>? = arrayListOf()

    @SerializedName("attributes")
    var attributes: ArrayList<AttributeRequest>? = arrayListOf()

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("published")
    var published = false

    @SerializedName("price")
    var price: Double? = null

    @SerializedName("product_cost")
    var productCost: Double? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        productId = parcel.readInt()

        //productCost = parcel.readDouble()
        //price = parcel.readDouble()
        //published = parcel.readByte() != 0.toByte()
        //attributes =
        //    parcel.readValue(Array<AttributeRequest>::class.java.classLoader) as? java.util.ArrayList<AttributeRequest>
        //productSpecificationAttributes =
        //    parcel.readValue(Array<ProductSpecificationAttributes>::class.java.classLoader) as? java.util.ArrayList<ProductSpecificationAttributes>
        //manufacturerIds =
        //    parcel.readValue(Array<Int>::class.java.classLoader) as? java.util.ArrayList<Int>
        //categoryIds =
        //    parcel.readValue(Array<Int>::class.java.classLoader) as? java.util.ArrayList<Int>
        //shortDescription = parcel.readString()
        //name = parcel.readString()
        //images =
        //    parcel.readValue(Array<ProductPicture>::class.java.classLoader) as? java.util.ArrayList<ProductPicture>
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(productId)
        parcel.writeValue(id)
        //parcel.writeValue(productCost)
        //parcel.writeValue(price)
        //parcel.writeByte(if (published) 1 else 0)
        //parcel.writeValue(attributes)
        //parcel.writeValue(productSpecificationAttributes)
        //parcel.writeValue(manufacturerIds)
        //parcel.writeValue(categoryIds)
        //parcel.writeString(shortDescription)
        //parcel.writeString(name)
        //parcel.writeValue(images)
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