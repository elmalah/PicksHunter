package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class ProductAttributeOption{
    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("Name")
    var name: String? = null

    var selected = false
}