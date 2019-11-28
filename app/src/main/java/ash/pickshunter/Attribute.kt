package ash.pickshunter

import ash.pickshunter.country.Option
import com.google.gson.annotations.SerializedName

class Attribute  {

    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Options")
    var options: ArrayList<Option>? = null
}