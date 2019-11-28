package ash.pickshunter

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Trip() : Parcelable {

    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("customer-id")
    var customerId: Int? = null

    @SerializedName("from-state-province-id")
    var fromStateProvinceId: Int? = null

    @SerializedName("to-state-province-id")
    var toStateProvinceId: Int? = null

    @SerializedName("from-date")
    var fromDate: String? = null

    @SerializedName("to-date")
    var toDate: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        customerId = parcel.readValue(Int::class.java.classLoader) as? Int
        fromStateProvinceId = parcel.readValue(Int::class.java.classLoader) as? Int
        toStateProvinceId = parcel.readValue(Int::class.java.classLoader) as? Int
        fromDate = parcel.readString()
        toDate = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(customerId)
        parcel.writeValue(fromStateProvinceId)
        parcel.writeValue(toStateProvinceId)
        parcel.writeString(fromDate)
        parcel.writeString(toDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trip> {
        override fun createFromParcel(parcel: Parcel): Trip {
            return Trip(parcel)
        }

        override fun newArray(size: Int): Array<Trip?> {
            return arrayOfNulls(size)
        }
    }
}