package ua.nure.chumchase.feature.profile.domain.model

data class CommentDTO(
    val author: ProfileDTO,
    val text: String,
    val dateTime: String
)
