package ash.pickshunter

import com.google.gson.annotations.SerializedName

class Address {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("address1")
    var address1: String? = null

    var selected: Boolean = false

    constructor(city: String?, address1: String?) {
        this.city = city
        this.address1 = address1
    }
}