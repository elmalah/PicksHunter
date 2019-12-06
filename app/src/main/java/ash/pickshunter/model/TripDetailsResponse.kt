package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class TripDetailsResponse() : Parcelable {

    @SerializedName("TripId")
    var tripId: Int? = null

    @SerializedName("FromDate")
    var FromDate: String? = null

    @SerializedName("ToDate")
    var ToDate: String? = null

    @SerializedName("Remaining")
    var remaining: String? = null

    @SerializedName("FlagUrl")
    var flagUrl: String? = null

    @SerializedName("Shops")
    var shops: ArrayList<Shop>? = null

    @SerializedName("Products")
    var products: ArrayList<ProductView>? = null

    @SerializedName("Orders")
    var orders: ArrayList<OrderView>? = null

    @SerializedName("From")
    var from: StateProvince? = null

    @SerializedName("To")
    var to: StateProvince? = null

    constructor(parcel: Parcel) : this() {
        tripId = parcel.readValue(Int::class.java.classLoader) as? Int
        FromDate = parcel.readString()
        ToDate = parcel.readString()
        remaining = parcel.readString()
        flagUrl = parcel.readString()
        from = parcel.readParcelable(StateProvince::class.java.classLoader)
        to = parcel.readParcelable(StateProvince::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(tripId)
        parcel.writeString(FromDate)
        parcel.writeString(ToDate)
        parcel.writeString(remaining)
        parcel.writeString(flagUrl)
        parcel.writeParcelable(from, flags)
        parcel.writeParcelable(to, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TripDetailsResponse> {
        override fun createFromParcel(parcel: Parcel): TripDetailsResponse {
            return TripDetailsResponse(parcel)
        }

        override fun newArray(size: Int): Array<TripDetailsResponse?> {
            return arrayOfNulls(size)
        }
    }

}


