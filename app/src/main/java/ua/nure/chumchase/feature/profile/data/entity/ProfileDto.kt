package ua.nure.chumchase.feature.profile.data.entity

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    val uid: String,
    val login: String,
    val email: String,
    @SerializedName("photo_url") val photoUrl: String?,
    @SerializedName("tag_list") val tags: List<String>?,
    val comments: List<CommentDto>?
)