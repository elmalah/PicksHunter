package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class ProductAttributeRequest {

    @SerializedName("product_attribute_id")
    var productAttributeId: Int? = null

    @SerializedName("is_required")
    var isRequired: Boolean = true

    @SerializedName("attribute_control_type_id")
    var attributeControlTypeId: Int = 1

    @SerializedName("attribute_values")
    var productAttributeValues: ArrayList<ProductAttributeValues> = arrayListOf()

    constructor(productAttributeId: Int?, productAttributeValues: ArrayList<ProductAttributeValues>) {
        this.productAttributeId = productAttributeId
        this.productAttributeValues = productAttributeValues
    }
}

class ProductAttributeValues {

    @SerializedName("type_id")
    var typeId = 0

    @SerializedName("name")
    var name: String? = null

    constructor(name: String?) {
        this.name = name
    }
}