package ua.nure.chumchase.feature.profile.data.entity

import com.google.gson.annotations.SerializedName

data class PlainCommentDto(@SerializedName("receiver_id") val receiverId: String, val text: String)
