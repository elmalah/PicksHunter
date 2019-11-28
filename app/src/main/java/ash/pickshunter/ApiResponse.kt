package ash.pickshunter

import com.google.gson.annotations.SerializedName

class ApiResponse {

    @SerializedName("IsSucceeded")
    var isSucceeded: Boolean = false

    @SerializedName("Customer")
    var user: User? = null

    @SerializedName("customers")
    var users: List<User>? = null

    var error: Throwable? = null

    @SerializedName("ErrorMessage")
    var message: String? = null

    @SerializedName("errors")
    var errors: Error? = null

    @SerializedName("manufacturers")
    var manufacturers: List<Manufacture>? = null

    @SerializedName("categories")
    var categories: List<Category>? = null


    constructor(error: Throwable) {
        this.error = error
    }
}