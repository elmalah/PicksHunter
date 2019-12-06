package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class AttributeOptionView() : Parcelable {

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Value")
    var value: String? = null


    constructor(parcel: Parcel) : this() {
        value = parcel.readString()
    }

    override fun toString(): String {
        return value.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AttributeOptionView> {
        override fun createFromParcel(parcel: Parcel): AttributeOptionView {
            return AttributeOptionView(parcel)
        }

        override fun newArray(size: Int): Array<AttributeOptionView?> {
            return arrayOfNulls(size)
        }
    }
}


