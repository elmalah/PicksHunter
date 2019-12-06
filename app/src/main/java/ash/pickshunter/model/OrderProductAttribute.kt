package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class OrderProductAttribute{
    @SerializedName("Key")
    var key: String? = null

    @SerializedName("Value")
    var value: String? = null
}