package ash.pickshunter.model

import com.google.gson.annotations.SerializedName

class TimelineResult {
    @SerializedName("Live")
    var live: ArrayList<ProductView> = arrayListOf()

    @SerializedName("Following")
    var following: ArrayList<ProductView> = arrayListOf()

    @SerializedName("Trending")
    var trending: ArrayList<ProductView> = arrayListOf()
}