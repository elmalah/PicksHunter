package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class Category {

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