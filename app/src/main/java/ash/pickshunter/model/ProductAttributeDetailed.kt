package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class ProductAttributeDetailed {
    @SerializedName("ProductAttributeMappingId")
    var productAttributeMappingId: Int? = null

    @SerializedName("ProductAttributeId")
    var productAttributeId: Int? = null

//    @SerializedName("Id")
//    var id: Int? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Options")

    var options: ArrayList<ProductAttributeDetailedOption>? = null
}

class ProductAttributeDetailedOption {
    @SerializedName("Key")
    var key: Int? = null

    @SerializedName("Value")
    var value: String? = null

    var selected = false
}

class ProductAttribute {
    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Options")
    var options: ArrayList<ProductAttributeOption>? = null
}

class SpecificationAttribute {
    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Options")
    var options: ArrayList<SpecificationAttributeOption>? = null
}
