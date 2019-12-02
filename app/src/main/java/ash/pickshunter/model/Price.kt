package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class Price {

    @SerializedName("store-price")
    var storePrice: Double? = null

    @SerializedName("store-sale")
    var storeSale: Double? = null

    @SerializedName("vat-percentage")
    var vatPercentage: Double? = null

    @SerializedName("earning-approach")
    var earningApproach: Int? = null

    @SerializedName("shopper-comission")
    var shopperComission: Double? = null
}