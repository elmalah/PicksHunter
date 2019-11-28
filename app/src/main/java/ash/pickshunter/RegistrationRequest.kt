package ash.pickshunter

import com.google.gson.annotations.SerializedName

class RegistrationRequest {

    @SerializedName("customer")
    var customer: User = User()
}