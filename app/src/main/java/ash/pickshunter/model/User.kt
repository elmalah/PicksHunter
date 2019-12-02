package ash.pickshunter.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class User() : Parcelable {

    @SerializedName("email")
    var email: String? = null

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("is_phone_verified")
    var isPhoneVerified: Boolean = false

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("password")
    var password: String? = null

    @SerializedName("role_ids")
    var role_ids: List<Int> = listOf(3)

    @SerializedName("addresses")
    var addresses: ArrayList<Address> = arrayListOf()

    constructor(parcel: Parcel) : this() {
        email = parcel.readString()
        firstName = parcel.readString()
        lastName = parcel.readString()
        phone = parcel.readString()
        isPhoneVerified = parcel.readByte() != 0.toByte()
        id = parcel.readInt()
        password = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(phone)
        parcel.writeByte(if (isPhoneVerified) 1 else 0)
        parcel.writeInt(id)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}