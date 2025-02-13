package dev.redfox.timepassdoggo.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DogsDataResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val imageUrl: String
)