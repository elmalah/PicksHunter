package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class TripRequest {

    @SerializedName("trip")
    var trip: Trip = Trip()
}