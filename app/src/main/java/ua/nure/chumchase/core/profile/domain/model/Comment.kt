package ua.nure.chumchase.core.profile.domain.model

data class Comment(
    val login: String,
    val commentText: String,
    val photo: String?,
    val dateTime: String
)
