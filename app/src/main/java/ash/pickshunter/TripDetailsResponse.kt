package ash.pickshunter

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class TripDetailsResponse {

    @SerializedName("tripId")
    var tripId: Int? = null

    @SerializedName("FromDate")
    var FromDate: String? = null

    @SerializedName("ToDate")
    var ToDate: String? = null

    @SerializedName("remaining")
    var remaining: String? = null

    @SerializedName("shops")
    var shops: ArrayList<Shop>? = null

    @SerializedName("from")
    var from: StateProvince? = null

    @SerializedName("to")
    var to: StateProvince? = null

//    var error: Throwable? = null
//    constructor(error: Throwable) {
//        this.error = error
//    }

//    constructor(parcel: Parcel) : this() {
//        tripId = parcel.readValue(Int::class.java.classLoader) as? Int
//        FromDate = parcel.readString()
//        ToDate = parcel.readString()
//        remaining = parcel.readString()
//        fromCountryAndState = parcel.readString()
//        toCountryAndState = parcel.readString()
//        tripCountryFlag = parcel.readString()
//        fromDate = parcel.readString()
//        toDate = parcel.readString()
//        shopName = parcel.readString()
//        description = parcel.readString()
//        price = parcel.readString()
//        shoperAvatar = parcel.readString()
//        name = parcel.readString()
//        shortDescription = parcel.readString()
//        id = parcel.readValue(Int::class.java.classLoader) as? Int
//        published = parcel.readByte() != 0.toByte()
//        productCost = parcel.readValue(Double::class.java.classLoader) as? Double
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeValue(productId)
//        parcel.writeString(shopperName)
//        parcel.writeString(productName)
//        parcel.writeString(date)
//        parcel.writeString(fromCountryAndState)
//        parcel.writeString(toCountryAndState)
//        parcel.writeString(tripCountryFlag)
//        parcel.writeString(fromDate)
//        parcel.writeString(toDate)
//        parcel.writeString(shopName)
//        parcel.writeString(description)
//        parcel.writeString(price)
//        parcel.writeString(shoperAvatar)
//        parcel.writeString(name)
//        parcel.writeString(shortDescription)
//        parcel.writeValue(id)
//        parcel.writeByte(if (published) 1 else 0)
//        parcel.writeValue(productCost)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Product> {
//        override fun createFromParcel(parcel: Parcel): Product {
//            return Product(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Product?> {
//            return arrayOfNulls(size)
//        }
//    }



}


class StateProvince() : Parcelable {

    @SerializedName("stateProvinceId")
    var stateProvinceId: Int? = null

    @SerializedName("stateProvinceName")
    var stateProvinceName: String? = null

    @SerializedName("countryId")
    var countryId: Int? = null

    @SerializedName("countryName")
    var countryName: String? = null



    constructor(parcel: Parcel) : this() {
        stateProvinceId = parcel.readValue(Int::class.java.classLoader) as? Int
        stateProvinceName = parcel.readString()
        countryId = parcel.readValue(Int::class.java.classLoader) as? Int
        countryName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(stateProvinceId)
        parcel.writeString(stateProvinceName)
        parcel.writeValue(countryId)
        parcel.writeString(countryName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<State> {
        override fun createFromParcel(parcel: Parcel): State {
            return State(parcel)
        }

        override fun newArray(size: Int): Array<State?> {
            return arrayOfNulls(size)
        }
    }
}