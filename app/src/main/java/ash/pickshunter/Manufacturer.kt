package ash.pickshunter

import com.google.gson.annotations.SerializedName

class Manufacturer {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("image")
    var Image: Image? = null

    var selected = false

    override fun toString(): String {
        return name.toString()
    }
}