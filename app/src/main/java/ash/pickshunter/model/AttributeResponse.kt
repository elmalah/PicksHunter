package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class AttributeResponse {

    @SerializedName("SpecificationAttributes")
    var specificationAttributes: ArrayList<Attribute>? = null

    @SerializedName("ProductAttributes")
    var productAttributes: ArrayList<Attribute>? = null
}