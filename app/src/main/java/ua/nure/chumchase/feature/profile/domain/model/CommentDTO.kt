package ua.nure.chumchase.feature.profile.domain.model

data class CommentDTO(
    val login: String,
    val text: String,
    val authorPhotoUrl: String?,
    val dateTime: String
)
