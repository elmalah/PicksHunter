package ash.pickshunter

import com.google.gson.annotations.SerializedName

class AttributeRequest {

    @SerializedName("product_attribute_id")
    var productAttributeId: Int? = null

    @SerializedName("is_required")
    var isRequired: Boolean = true

    @SerializedName("attribute_control_type_id")
    var attributeControlTypeId: Int = 1

    @SerializedName("attribute_values")
    var attribute_values: ArrayList<AttributeValues> = arrayListOf()

    constructor(productAttributeId: Int?, attribute_values: ArrayList<AttributeValues>) {
        this.productAttributeId = productAttributeId
        this.attribute_values = attribute_values
    }
}

class AttributeValues {

    @SerializedName("type_id")
    var typeId = 0

    @SerializedName("name")
    var name: String? = null

    constructor(name: String?) {
        this.name = name
    }
}