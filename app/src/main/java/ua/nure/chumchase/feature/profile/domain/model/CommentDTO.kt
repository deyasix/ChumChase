package ua.nure.chumchase.feature.profile.domain.model

data class CommentDTO(
    val author: UserInfoDTO,
    val text: String,
    val dateTime: String
)
