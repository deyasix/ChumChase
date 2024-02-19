package ua.nure.chumchase.feature.profile.data.entity

import com.google.gson.annotations.SerializedName

data class CommentSenderDto(
    @SerializedName("_id") val id: String,
    val login: String,
    @SerializedName("photo_url") val photoUrl: String?
)