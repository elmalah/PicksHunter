package ash.pickshunter.model

import com.google.gson.annotations.SerializedName


class Profile {
    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Avatar")
    val avatar: String? = null

    @SerializedName("OriginLocation")
    val originLocation: String? = null

    @SerializedName("Rating")
    val rating: Float? = null

    @SerializedName("Followed")
    val followed: Boolean? = null

    @SerializedName("NumberOfFollowers")
    val numberOfFollowers: Int? = null

    @SerializedName("NumberOfTrips")
    val numberOfTrips: Int? = null

    @SerializedName("NumberOfProducts")
    val numberOfProducts: Int? = null

    @SerializedName("Products")
    val products: ArrayList<ProductView>? = arrayListOf()
}
