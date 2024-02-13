package ua.nure.chumchase.feature.profile.data.entity

import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("_id") val id: String,
    @SerializedName("receiver_id") val receiverId: String,
    val sender: CommentSenderDto,
    val text: String, val date: String
)