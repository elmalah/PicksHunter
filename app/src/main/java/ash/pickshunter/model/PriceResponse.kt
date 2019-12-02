package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class PriceResponse {

    @SerializedName("currency-id")
    var currencyId: Int? = null

    @SerializedName("store-price")
    var storePrice: Int? = null

    @SerializedName("vat-amount")
    var vatAmount: Double? = null

    @SerializedName("app-comission")
    var appComission: Double? = null

    @SerializedName("total-price")
    var totalPrice: Double? = null

    @SerializedName("total-price-egp")
    var totalPriceEgp: Double? = null

    @SerializedName("cost")
    var cost: Double? = null

    @SerializedName("cost-egp")
    var costEgp: Double? = null
}