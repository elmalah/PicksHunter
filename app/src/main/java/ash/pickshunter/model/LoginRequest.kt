package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class LoginRequest {

    @SerializedName("Email")
    var Email: String? = null

    @SerializedName("Password")
    var Password: String? = null
}