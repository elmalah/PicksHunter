package ash.pickshunter

import com.google.gson.annotations.SerializedName
import java.io.File

class PictureRequest {
    @SerializedName("fileUri")
    var fileUri: String? = null

    @SerializedName("file")
    var file: File? = null
}