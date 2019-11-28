package ash.pickshunter

import com.google.gson.annotations.SerializedName

class ProductSpecificationAttributes {

    @SerializedName("attribute_type_id")
    var attributeTypeId: Int = 0

    @SerializedName("show_on_product_page")
    var showOnProductPage: Boolean = true

    @SerializedName("specification_attribute_option_id")
    var specification_attribute_option_id: Int? = null

    constructor(specification_attribute_option_id: Int?) {
        this.specification_attribute_option_id = specification_attribute_option_id
    }
}