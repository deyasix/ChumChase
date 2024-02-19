package ua.nure.chumchase.feature.profile.domain.model

data class Comment(
    val id: String,
    val senderId: String,
    val senderPhotoUrl: String?,
    val senderLogin: String,
    val receiverId: String,
    val text: String,
    val date: String
)