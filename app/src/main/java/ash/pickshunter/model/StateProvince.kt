package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class StateProvince() : Parcelable {

    @SerializedName("StateProvinceId")
    var stateProvinceId: Int? = null

    @SerializedName("StateProvinceName")
    var stateProvinceName: String? = null

    @SerializedName("CountryId")
    var countryId: Int? = null

    @SerializedName("CountryName")
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