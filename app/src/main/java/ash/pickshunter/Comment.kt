package ash.pickshunter

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Comment() : Parcelable {

    @SerializedName("CustomerName")
    var customerName: String? = null

    @SerializedName("Comment")
    var comment: String? = null

    @SerializedName("FriendlyDate")
    var friendlyDate: String? = null

    constructor(parcel: Parcel) : this() {
        customerName = parcel.readString()
        comment = parcel.readString()
        friendlyDate = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(customerName)
        parcel.writeString(comment)
        parcel.writeString(friendlyDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }
}